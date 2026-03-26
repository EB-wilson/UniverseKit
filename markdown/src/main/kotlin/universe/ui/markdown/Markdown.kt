package universe.ui.markdown

import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.event.Touchable
import arc.scene.style.Drawable
import arc.scene.ui.ScrollPane
import arc.scene.ui.layout.WidgetGroup
import arc.struct.ObjectMap
import arc.struct.Seq
import arc.util.pooling.Pool.Poolable
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import org.commonmark.Extension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.parser.Parser.ParserExtension
import universe.ui.markdown.MDLayoutRenderer.DrawRendererExtension

fun Markdown(
  md: String,
  style: Markdown.MarkdownStyle,
) = Markdown(BaseProvider(), md, style)

/**Markdown文档渲染元素。*/
open class Markdown<P: MarkdownProvider> : WidgetGroup {
  private val markdownDraws = Seq<MarkdownDraw>()

  private var node: Node
  private var style: MarkdownStyle?

  private var prefInvalid: Boolean = true
  private var buildActObjs: Boolean = false
  private var lastPrefHeight: Float = 0f

  internal val parser: Parser?
  internal val renderer: MDLayoutRenderer

  internal var provider: P
  internal var rendererContext: RendererContext

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

  private fun checkExtensions(extensions: List<Extension>) {
    for (extension in extensions) {
      require(extension is DrawRendererExtension || extension is ParserExtension) {
        "extension must be a DrawRendererExtension or a ParserExtension"
      }
    }
  }

  fun setProvider(provider: P) {
    this.provider = provider
    this.rendererContext = renderer.createContext(this)
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

  private fun renderLayout(): Boolean {
    renderer.renderLayout(node)

    prefInvalid = false

    if (rendererContext.prefHeight != lastPrefHeight) {
      lastPrefHeight = rendererContext.prefHeight
      invalidateHierarchy()

      return false
    }

    return true
  }

  override fun layout() {
    for (obj in markdownDraws) {
      obj.free()
    }
    markdownDraws.clear()

    if (!renderLayout()) return

    markdownDraws.addAll(rendererContext.renderResult())
    markdownDraws.sort{ a, b -> a.priority() - b.priority() }

    buildActObjs = true
    clearChildren()
    for (obj in markdownDraws) {
      if (obj is ActivityDrawer) {
        val element = obj.getActiveElement()
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
    return 0f
  }

  override fun getPrefHeight(): Float {
    if (prefInvalid) renderLayout()
    return rendererContext.prefHeight
  }

  override fun drawChildren() {
    for (obj in markdownDraws) {
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

  data class FontEntry(
    val fontModifier: Font? = null,
    val colorModifier: Color? = null,
    val scaleModifier: Float? = null,
  )

  data class Box(
    val background: Drawable? = null,
    val paddingLeft: Float = 0f,
    val paddingRight: Float = 0f,
    val paddingTop: Float = 0f,
    val paddingBottom: Float = 0f,
    val marginLeft: Float = 0f,
    val marginRight: Float = 0f,
    val marginTop: Float = 0f,
    val marginBottom: Float = 0f,
  ){
    constructor(
      background: Drawable? = null,
      paddingX: Float = 0f,
      paddingY: Float = 0f,
      marginX: Float = 0f,
      marginY: Float = 0f,
    ): this(
      background = background,
      paddingLeft = paddingX,
      paddingRight = paddingX,
      paddingTop = paddingY,
      paddingBottom = paddingY,
      marginLeft = marginX,
      marginRight = marginX,
      marginTop = marginY,
      marginBottom = marginY,
    )
  }

  class MarkdownStyle {
    private companion object{
      val defaultFont = FontEntry(Fonts.def, Color.white, 1f)
      val defaultBox = Box()
    }

    //globals
    var linesPadding: Float = 0f
    var paragraphPadding: Float = 0f

    var lineColor: Color = Color.white
    var lineStroke: Float = 0f

    //normal
    var textFont: FontEntry = defaultFont
    var subFont: FontEntry = defaultFont
    var emFont: FontEntry = defaultFont
    var strongFont: FontEntry = defaultFont
    var headFonts: Array<FontEntry> = arrayOf()
    var quoteBox: Box = defaultBox
    var curtain: Box = defaultBox

    //code
    var codeFont: FontEntry = defaultFont
    var codeBox: Box = defaultBox
    var codeBlockBox: Box = defaultBox
    var codeBlockSliderStyle: ScrollPane.ScrollPaneStyle = ScrollPane.ScrollPaneStyle()

    //link
    var linkFont: FontEntry = defaultFont
    var linkOverColor: Color = Color.white

    //list
    var listOrderFont: FontEntry = defaultFont
    var listItemBox: Box = defaultBox
    var listItemHeadBox: Box = defaultBox
    var bulletListMarks: Array<Drawable> = arrayOf()
    var orderedListFormatters: Array<(Int) -> String> = arrayOf()

    //table
    var tableBack1: Box = defaultBox
    var tableBack2: Box = defaultBox
  }

  abstract class MarkdownDraw : Poolable {
    var offsetX: Float = 0f
    var offsetY: Float = 0f

    var width: Float = 0f
    var height: Float = 0f

    abstract fun prefWidth(): Float
    abstract fun prefHeight(): Float

    abstract fun setup(scope: RendererContext.Scope)
    abstract fun draw(x: Float, y: Float)

    open fun priority(): Int {
      return 0
    }

    override fun reset() {
      offsetY = 0f
      offsetX = 0f
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
    fun getActiveElement(): Element
  }
}
