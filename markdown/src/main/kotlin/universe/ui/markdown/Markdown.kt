package universe.ui.markdown

import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.event.Touchable
import arc.scene.style.Drawable
import arc.scene.ui.ScrollPane.ScrollPaneStyle
import arc.scene.ui.layout.WidgetGroup
import arc.struct.Seq
import arc.util.pooling.Pool.Poolable
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import org.commonmark.Extension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.parser.Parser.ParserExtension
import universe.ui.markdown.MDLayoutRenderer.DrawRendererExtension
import javax.swing.text.Highlighter

fun Markdown(
  md: String,
  style: Markdown.MarkdownStyle,
) = Markdown(BaseProvider(), md, style)

/**Markdown文档渲染元素。*/
open class Markdown<P: MarkdownProvider> : WidgetGroup {
  companion object{
    val IMAGE_BASE_64_List: Array<String?> = arrayOf<String?>(
      "data:image/png;base64,",
      "data:image/jpg;base64,",
      "data:image/jpeg;base64,"
    )

    private fun checkExtensions(extensions: List<Extension>) {
      for (extension in extensions) {
        require(!(extension !is DrawRendererExtension || extension !is ParserExtension)) { "extension must be a DrawRendererExtension or a ParserExtension" }
      }
    }
  }

  private val drawObjs = Seq<DrawObj>()

  private var node: Node
  private var style: MarkdownStyle?

  private var prefWidth: Float = 0f
  private var prefHeight: Float = 0f

  private var prefInvalid: Boolean = true
  private var buildActObjs: Boolean = false
  private var lastPrefHeight: Float = 0f

  internal var provider: P
  internal val parser: Parser?
  internal val renderer: MDLayoutRenderer
  internal val rendererContext: RendererContext

  internal constructor(provider: P, md: String, style: MarkdownStyle) {
    val extensions = provider.extensions()
    checkExtensions(extensions)

    this.provider = provider
    this.style = style

    parser = Parser.builder()
      .extensions(extensions)
      .build()
    renderer = MDLayoutRenderer.builder()
      .extensions(extensions)
      .build()

    rendererContext = renderer.createContext(this)

    node = parser.parse(md)
    touchable = Touchable.childrenOnly
  }

  /**internal usage
   *
   * @hidden
   */
  constructor(parent: Markdown<P>, node: Node) {
    this.provider = parent.provider
    parser = null
    renderer = MDLayoutRenderer.builder().extensions(provider.extensions()).build()
    rendererContext = renderer.createContext(this)

    this.node = node
    touchable = Touchable.childrenOnly

    this.style = parent.getStyle()
  }

  fun setProvider(provider: P) {
    this.provider = provider
    invalidate()
  }

  fun getProvider(): P = provider

  fun setDocument(string: String) {
    node = parser!!.parse(string)
    invalidate()
  }

  fun setStyle(style: MarkdownStyle) {
    this.style = style
    invalidate()
  }

  fun getStyle(): MarkdownStyle {
    return style!!
  }

  private fun calculatePrefSize() {
    prefWidth = 0f
    prefHeight = 0f

    renderer.renderLayout(node)

    prefWidth = rendererContext.prefWidth
    prefHeight += rendererContext.prefHeight

    prefInvalid = false

    if (prefHeight != lastPrefHeight) {
      lastPrefHeight = prefHeight
      invalidateHierarchy()
    }
  }

  override fun layout() {
    for (obj in drawObjs) {
      obj.free()
    }
    drawObjs.clear()

    renderer.renderLayout(node)

    drawObjs.addAll(rendererContext.renderResult())
    drawObjs.sort{ a, b -> a.priority() - b.priority() }

    buildActObjs = true
    clearChildren()
    for (obj in drawObjs) {
      if (obj is ActivityDrawer) {
        val element = obj.elem
        addChild(element)
        element.setBounds(
          obj.offsetX,
          height - obj.offsetY - obj.height,
          obj.width,
          obj.height
        )
        element.validate()
      }
    }
    buildActObjs = false
  }

  override fun childrenChanged() {
    if (!buildActObjs) invalidateHierarchy()
  }

  override fun invalidate() {
    super.invalidate()
    prefInvalid = true
  }

  override fun getPrefWidth(): Float {
    if (prefInvalid) calculatePrefSize()
    return prefWidth
  }

  override fun getPrefHeight(): Float {
    if (prefInvalid) calculatePrefSize()
    return prefHeight
  }

  override fun drawChildren() {
    for (obj in drawObjs) {
      if (obj is ActivityDrawer && cullingArea != null && !cullingArea.overlaps(
        obj.offsetX,
        height + obj.offsetY,
        obj.width,
        obj.height
      )) continue

      Draw.reset()
      Draw.alpha(parentAlpha)
      obj.draw(x, y + height)
    }
    super.drawChildren()
  }

  open class MarkdownStyle {
    var linesPadding: Float = 0f
    var paragraphPadding: Float = 0f

    var tablePadHor: Float = 0f
    var tablePadVert: Float = 0f

    lateinit var font: Font
    lateinit var subFont: Font
    lateinit var codeFont: Font
    lateinit var emFont: Font
    lateinit var strongFont: Font
    lateinit var textColor: Color
    lateinit var emColor: Color
    lateinit var subTextColor: Color
    lateinit var lineColor: Color
    lateinit var linkColor: Color

    lateinit var codeBlockStyle: ScrollPaneStyle
    lateinit var listMarks: Array<Drawable>

    lateinit var board: Drawable
    lateinit var codeBack: Drawable
    lateinit var codeBlockBack: Drawable
    lateinit var tableBack1: Drawable
    lateinit var tableBack2: Drawable
    lateinit var curtain: Drawable
    lateinit var highlighter: Highlighter
  }

  abstract class DrawObj : Poolable {
    var offsetX: Float = 0f
    var offsetY: Float = 0f

    var width: Float = 0f
    var height: Float = 0f

    abstract fun prefWidth(): Float
    abstract fun prefHeight(): Float

    abstract fun setup()
    abstract fun draw(x: Float, y: Float)

    open fun priority(): Int {
      return 0
    }

    override fun reset() {
      offsetY = 0f
      offsetX = offsetY
    }

    fun free() {
      Pools.free(this)
    }

    companion object {
      @JvmStatic protected val tmp1: Color = Color()
      @JvmStatic protected val tmp2: Color = Color()
    }
  }

  interface ActivityDrawer {
    val elem: Element
  }
}
