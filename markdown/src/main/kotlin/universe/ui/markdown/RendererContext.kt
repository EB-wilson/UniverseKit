package universe.ui.markdown

import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.style.Drawable
import arc.scene.ui.layout.Scl
import arc.struct.Seq
import jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent
import mindustry.ui.Fonts
import org.commonmark.node.Node
import universe.ui.markdown.Markdown.DrawObj
import kotlin.math.max

abstract class RendererContext protected constructor(
  private val element: Markdown<*>
) {
  private val drawObjs = Seq<DrawObj>()
  private var currentScope: Scope? = null
  private var rootScope: Scope? = null

  val prefWidth: Float get() = rootScope?.let { it.width + it.paddingX*2}?:0f
  val prefHeight: Float = rootScope?.let { it.height + it.paddingY*2}?:0f

  val mdStyle = element.getStyle()
  val mdWidth = element.width
  val mdHeight = element.height

  abstract fun render(node: Node)

  class Scope internal constructor(
    val background: Drawable? = null,
    val parent: Scope? = null,
    val paddingX: Float,
    val paddingY: Float,
    val marginX: Float,
    val marginY: Float,
    val maxWidth: Float
  ): DrawObj(){
    init {
      this.offsetX = (parent?.offsetX?:0f) + paddingX
      this.offsetY = (parent?.currOffsetY?:0f) + paddingY
    }

    var currOffsetX: Float = (parent?.currOffsetX?:offsetX) + marginX
    var currOffsetY: Float = offsetY + marginY

    var rowHeight: Float = 0f

    var font: Font = Fonts.def
    var fontColor: Color = Color.white
    var fontScale: Float = 1f

    override fun prefWidth(): Float = 0f
    override fun prefHeight(): Float = 0f

    override fun setup() {}

    override fun draw(x: Float, y: Float) {
      background?.draw(x, y, width, height)
    }
  }

  fun init() {
    rootScope = null
    currentScope = null
    drawObjs.clear()
  }

  fun getScope(): Scope = currentScope?: insertScope()

  fun withScope(
    background: Drawable? = null,
    paddingX: Float = 0f,
    paddingY: Float = 0f,
    marginX: Float = 0f,
    marginY: Float = 0f,
    maxWidth: Float = (currentScope?.maxWidth?:0f) - paddingX*2,
    callback: Scope.() -> Unit
  ) {
    insertScope(
      background,
      paddingX,
      paddingY,
      marginX,
      marginY,
      maxWidth,
    ).callback()
    popScope()
  }

  fun insertScope(
    background: Drawable? = null,
    paddingX: Float = 0f,
    paddingY: Float = 0f,
    marginX: Float = 0f,
    marginY: Float = 0f,
    maxWidth: Float = (currentScope?.maxWidth?:0f) - paddingX*2,
  ): Scope {
    val scope = Scope(
      background,
      currentScope,
      paddingX,
      paddingY,
      marginX,
      marginY,
      maxWidth,
    )

    if (currentScope == null) {
      rootScope = scope
    }

    drawObjs.add(scope)
    currentScope = scope

    return scope
  }

  fun popScope(){
    val curr = currentScope?:
      throw IllegalStateException("Current has no scope be set.")
    val last = curr.parent

    if (last != null) {
      last.height = max(last.height, curr.offsetY + curr.height - last.offsetY + last.paddingY)
      last.currOffsetX += curr.width + curr.paddingX
    }

    currentScope = last
  }

  fun draw(drawObj: DrawObj) {
    val curr = getScope()
    drawObj.setup()

    drawObj.width = drawObj.prefWidth()
    drawObj.height = drawObj.prefHeight()

    if (curr.maxWidth > 0 && curr.currOffsetX + drawObj.width > curr.maxWidth && curr.maxWidth > drawObj.width) {
      row(mdStyle.linesPadding)
    }

    drawObj.offsetX = curr.currOffsetX
    drawObj.offsetY = curr.currOffsetY

    if (curr.maxWidth > 0 && drawObj.offsetX + drawObj.width > curr.maxWidth) {
      val srkW = curr.maxWidth - drawObj.offsetX
      val ratio = srkW/drawObj.width

      drawObj.width = srkW
      drawObj.height *= ratio
    }

    drawObjs.add(drawObj)

    curr.currOffsetX += drawObj.width
    curr.width = max(curr.width, drawObj.offsetX + drawObj.width - curr.offsetX + curr.marginX)
    curr.height = max(curr.height, drawObj.offsetY + drawObj.height - curr.offsetY + curr.marginY)
    curr.rowHeight = max(curr.rowHeight, drawObj.height)
  }

  fun row(rowPadding: Float) {
    val curr = getScope()

    curr.currOffsetX = curr.offsetX + curr.marginX
    curr.currOffsetY += curr.rowHeight + Scl.scl(rowPadding)
    curr.rowHeight = 0f
  }

  fun renderResult() = drawObjs.toList()
}