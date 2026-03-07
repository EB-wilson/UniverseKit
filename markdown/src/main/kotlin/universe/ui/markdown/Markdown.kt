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
import org.commonmark.Extension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.parser.Parser.ParserExtension
import universe.ui.markdown.MDLayoutRenderer.DrawRendererExtension
import javax.swing.text.Highlighter
import kotlin.math.max

/**Markdown文档渲染元素。*/
open class Markdown<P: MarkdownProvider> : WidgetGroup {
  private val drawObjs = Seq<DrawObj>()

  private var node: Node
  private var style: MarkdownStyle?

  var prefWidth: Float = 0f
  var prefHeight: Float = 0f
  var prefInvalid: Boolean = true
  var contentWrap: Boolean = true
  var lastPrefHeight: Float = 0f

  internal var provider: P
  internal val parser: Parser?
  internal val renderer: MDLayoutRenderer
  internal val rendererContext: RendererContext

  constructor(md: String, style: MarkdownStyle, provider: P) {
    val extensions = provider.extensions()
    checkExtensions(extensions)

    this.provider = provider
    parser = Parser.builder().extensions(extensions).build()
    renderer = MDLayoutRenderer.builder().extensions(extensions).build()
    rendererContext = renderer.createContext(this)

    node = parser.parse(md)
    touchable = Touchable.childrenOnly

    this.style = style
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

  fun setContentWrap(wrap: Boolean) {
    contentWrap = wrap
  }

  private fun calculatePrefSize(layoutStep: Boolean) {
    rendererContext.prefSizeCalculating = !layoutStep

    prefWidth = 0f
    prefHeight = prefWidth

    for (obj in drawObjs) {
      obj.free()
    }
    drawObjs.clear()

    renderer.renderLayout(node)

    prefWidth = max(prefWidth, rendererContext.rendOff)
    prefHeight += rendererContext.totalHeight

    prefInvalid = false

    if (prefHeight != lastPrefHeight) {
      lastPrefHeight = prefHeight
      invalidateHierarchy()
    }

    rendererContext.prefSizeCalculating = false
  }

  override fun layout() {
    calculatePrefSize(true)

    drawObjs.addAll(rendererContext.renderResult())
    drawObjs.sort(Comparator { a: DrawObj?, b: DrawObj? -> a!!.priority() - b!!.priority() })

    clearChildren()
    for (obj in drawObjs) {
      if (obj is ActivityDrawer) {
        val element = obj.elem
        addChild(element)
        element.setBounds(
          obj.offsetX,
          height + obj.offsetY - obj.height(),
          obj.width(),
          obj.height()
        )
        element.validate()
      }
    }
  }

  override fun getPrefWidth(): Float {
    if (contentWrap) return 0f

    if (prefInvalid) calculatePrefSize(false)
    return prefWidth
  }

  override fun getPrefHeight(): Float {
    if (prefInvalid) calculatePrefSize(false)
    return prefHeight
  }

  override fun drawChildren() {
    for (obj in drawObjs) {
      if (obj is ActivityDrawer && cullingArea != null && !cullingArea.overlaps(
          obj.offsetX,
          height + obj.offsetY,
          obj.width(),
          obj.height()
        )
      ) continue

      Draw.reset()
      Draw.alpha(parentAlpha)
      obj.draw()
    }
    super.drawChildren()
  }

  open class MarkdownStyle {
    var font: Font? = null
    var codeFont: Font? = null
    var emFont: Font? = null
    var strongFont: Font? = null
    var subFont: Font? = null
    var textColor: Color? = null
    var emColor: Color? = null
    var subTextColor: Color? = null
    var lineColor: Color? = null
    var linkColor: Color? = null
    var linesPadding: Float = 0f
    var tablePadHor: Float = 0f
    var tablePadVert: Float = 0f
    var paragraphPadding: Float = 0f
    var board: Drawable? = null
    var codeBack: Drawable? = null
    var codeBlockBack: Drawable? = null
    var tableBack1: Drawable? = null
    var tableBack2: Drawable? = null
    var curtain: Drawable? = null
    var codeBlockStyle: ScrollPaneStyle? = null
    var listMarks: Array<Drawable>? = null
    var highlighter: Highlighter? = null
  }

  abstract class DrawObj : Poolable {
    protected var parent: Markdown<*>? = null
    var offsetX: Float = 0f
    var offsetY: Float = 0f

    abstract fun draw()

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

    fun width(): Float

    fun height(): Float
  }

  companion object {
    val IMAGE_BASE_64_List: Array<String?> = arrayOf<String?>(
      "data:image/png;base64,",
      "data:image/jpg;base64,",
      "data:image/jpeg;base64,"
    )

    private fun checkExtensions(extensions: List<Extension>) {
      for (extension in extensions) {
        require(!(extension !is DrawRendererExtension || extension !is ParserExtension)) { "extension must be a DrawRendererExtension and a ParserExtension" }
      }
    }
  }
}
