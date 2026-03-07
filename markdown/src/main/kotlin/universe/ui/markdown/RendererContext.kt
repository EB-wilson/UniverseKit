package universe.ui.markdown

import arc.Core
import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.actions.Actions
import arc.scene.style.Drawable
import arc.scene.ui.Tooltip
import arc.struct.Seq
import mindustry.ui.Fonts
import org.commonmark.node.Node
import universe.ui.markdown.Markdown.DrawObj
import universe.ui.markdown.elemdraw.DrawBoard
import universe.ui.markdown.elemdraw.DrawClickable
import universe.ui.markdown.elemdraw.DrawCode
import universe.ui.markdown.elemdraw.DrawStr
import kotlin.math.max

abstract class RendererContext protected constructor(
  val element: Markdown<*>
) {
  private val drawObjs = Seq<DrawObj>()

  var prefSizeCalculating: Boolean = false

  /**面板堆叠计数器，在绘制带有背景面板的对象时记录层序使用，应正确分配其序号 */
  var boardLayers: Int = 0
  /**列表堆叠计数器，在绘制带有缩进的列表时记录嵌套次数使用 */
  var listLayer: Int = 0
  /**当前绘制对象的左侧边距值（单位为空格字符的宽度），通常在嵌套块中使用，该值在换行时会用于计算渲染对象的左侧偏移量 */
  var padding: Float = 0f
  /**当前绘制对象使用的比例缩放器（若可用） */
  var currScl: Float = 0f
  /**当前绘制对象的横向坐标偏移量标尺，通常由换行动作计算获得 */
  var rendOff: Float = 0f
  /**当前绘制对象的纵向坐标偏移量标尺（自上到下），通常由换行动作计算获得 */
  var lineOff: Float = 0f
  /**当前文档的总高度，通常由换行动作计算获得 */
  var totalHeight: Float = 0f
  /**上一次绘制的文本信息映像 */
  var lastText: TextMirror? = null
  /**当前文本绘制时所使用的字体，通常用于嵌套块定义内容字体 */
  var currFont: Font? = null
  /**当前文本绘制时所使用的颜色，通常用于嵌套块定义内容颜色 */
  var currFontColor: Color? = null

  val style get() = element.getStyle()
  val width get() = element.getWidth()
  val height get() = element.getHeight()
  val isWarp get() = element.contentWrap

  /**渲染一个Markdown语法节点
   *
   * @param node 待渲染节点
   */
  abstract fun render(node: Node)

  /**获取渲染结果的可迭代对象 */
  fun renderResult(): Iterable<DrawObj> {
    return drawObjs
  }

  /**添加一个绘制对象 */
  fun draw(obj: DrawObj) {
    drawObjs.add(obj)
  }

  /**初始化渲染上下文 */
  fun init() {
    boardLayers = 0
    listLayer = 0
    padding = 0f
    rendOff = 0f
    lineOff = 0f
    currScl = 1f
    totalHeight = 0f
    lastText = null
    currFont = style.font
    currFontColor = style.textColor
    drawObjs.clear()
  }

  /**更新文档显示区的高度（不会降低）
   *
   * @param h 显示位置的高度*/
  fun updateHeight(h: Float) {
    totalHeight = max(totalHeight, h)
  }

  /**@see RendererContext.makeStr*/
  fun makeStr(str: String, font: Font, color: Color): TextMirror {
    return makeStr(str, font, null, null, color)
  }

  /**@see RendererContext.makeStr*/
  fun makeStr(str: String, font: Font, color: Color, openUrl: String): TextMirror {
    return makeStr(str, font, openUrl, null, color)
  }

  /**@see RendererContext.makeStr*/
  fun makeStr(str: String, font: Font, background: Drawable?, color: Color): TextMirror {
    return makeStr(str, font, null, background, color)
  }

  /**添加一段文本显示器，并返回它的[边界信息映射对象][TextMirror]
   *
   * @param str 显示文本内容
   * @param font 文本字体
   * @param openUrl 文本超链接，为null则不定义超链接
   * @param background 文本背景，为null则透明
   * @param color 文本颜色*/
  fun makeStr(str: String, font: Font, openUrl: String?, background: Drawable?, color: Color): TextMirror {
    val maxWidth = if (!isWarp || width <= font.spaceXadvance*3) Float.MAX_VALUE
                   else width - rendOff
    var tmp = 0f
    var index = 0

    var width = 0f
    updateHeight(font.lineHeight*currScl)
    for (c in str.chars().toArray()) {
      val glyph = font.getData().getGlyph(c.toChar())
      if (glyph == null) {
        index++
        continue
      }

      tmp += glyph.xadvance*currScl*font.scaleX
      if (tmp > maxWidth) {
        break
      }
      width = tmp
      index++
    }

    var res = lastText
    if (width > 0) {
      rendOff += background?.leftWidth ?: 0f
      if (res == null) res = TextMirror(str.take(index), font, color, rendOff, -lineOff, width, totalHeight)
      else res.sub = TextMirror(str.take(index), font, color, rendOff, -lineOff, width, totalHeight)

      draw(
        if (openUrl != null) DrawClickable.get(
          element,
          str.take(index),
          font,
          { Core.app.openURI(openUrl) },
          Tooltip { t ->
            t.table(style.board).get().add(openUrl)
          },
          color,
          rendOff,
          -lineOff,
          currScl
        )
        else DrawStr.get(
          element,
          str.take(index),
          font,
          color,
          rendOff,
          -lineOff,
          currScl,
          background
        )
      )
      rendOff += width + (background?.rightWidth ?: 0f) + font.spaceXadvance*font.scaleX
    }
    else if (res == null) res = TextMirror("", font, color, rendOff, -lineOff, 0f, totalHeight)
    else res.sub = TextMirror("", font, color, rendOff, -lineOff, 0f, totalHeight)

    lastText = res

    if (index < str.length) {
      row()
      res.sub = makeStr(str.substring(index), font, openUrl, background, color)
    }

    return res
  }

  /**添加一个代码块显示区域
   *
   * @param lang 代码采用的语言标签
   * @param code 代码内容*/
  fun makeCodeBox(lang: String, code: String) {
    val style = style

    padding += 4f
    lineOff += style.linesPadding
    row()
    val begin = lineOff
    lineOff += style.linesPadding*2
    val pane = DrawCode.get(element, lang, code, style.codeFont!!, rendOff, -lineOff, style.codeBlockStyle!!)
    updateHeight(pane.height() + style.linesPadding)
    draw(pane)
    padding -= 4f
    row()
    lineOff += style.linesPadding*2
    draw(DrawBoard.get(element, style.codeBlockBack!!, boardLayers, lineOff - begin, rendOff, -begin))

    val c = DrawClickable.get(
      element, Core.bundle.get("editor.copy"), Fonts.outline,
      { Core.app.clipboardText = code },
      null, style.subTextColor!!, pane.width() - 64, -begin - style.linesPadding*2 - 8, 1f
    )
    draw(c)
    val e = c.elem
    e.color.a = 0.4f
    e.hovered { e.actions(Actions.alpha(1f, 0.5f)) }
    e.exited { e.actions(Actions.alpha(0.4f, 0.5f)) }
  }

  /**执行换行，该操作将更新文档区域的高度并重置行偏移标尺 */
  fun row() {
    val style = style
    element.prefWidth = max(element.prefWidth, rendOff)

    rendOff = padding*style.font!!.spaceXadvance
    lineOff += totalHeight + style.linesPadding*currScl

    element.prefHeight = lineOff
    totalHeight = 0f
  }
}