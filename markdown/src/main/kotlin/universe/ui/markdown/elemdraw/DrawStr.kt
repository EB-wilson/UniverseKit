package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.graphics.g2d.FontCache
import arc.graphics.g2d.GlyphLayout
import arc.util.Align
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import universe.ui.markdown.Markdown.DrawObj
import universe.ui.markdown.RendererContext

open class DrawStr internal constructor() : DrawObj() {
  var text: String = ""
  var font: Font = Fonts.def
  var scl: Float = 0f
  var color: Color = Color.white
  var remWidth: Float = 0f

  private var cache: FontCache? = null
  private var layout: GlyphLayout? = null

  override fun prefWidth(): Float = layout?.width?:0f
  override fun prefHeight(): Float = layout?.height?:0f

  override fun setup() {
    val data = font.getData()
    val lastScl = data.scaleX
    data.setScale(scl)
    cache = font.newFontCache()
    layout = cache!!.setText(
      text,
      0f, 0f,
      0, text.length,
      remWidth,
      Align.topLeft,
      false,
      "..."
    )
    data.setScale(lastScl)
  }

  override fun draw(x: Float, y: Float) {
    cache!!.tint(tmp1.set(color).mul(Draw.getColor()))
    cache!!.setPosition(
      x + offsetX,
      y - offsetY
    )

    cache!!.draw()
  }

  override fun reset() {
    super.reset()
    text = ""
    font = Fonts.def
    scl = 0f
    color = Color.white
    remWidth = 0f
  }

  companion object {
    fun get(
      str: String,
      font: Font,
      color: Color,
      scl: Float,
      remWidth: Float,
    ): DrawStr {
      val res = Pools.obtain(DrawStr::class.java) { DrawStr() }
      res.text = str
      res.font = font
      res.scl = scl
      res.color = color
      res.remWidth = remWidth

      return res
    }

    fun RendererContext.addTextWrap(
      str: String,
      url: String? = null,
      font: Font,
      color: Color,
      scl: Float
    ) {
      val data = font.getData()
      val curr = getScope()

      var builder = StringBuilder()
      var splitIndex = -1
      var nonSpaceNow = false

      var currOff = curr.currOffsetX
      var currIndex = 0

      fun addStr(s: CharSequence){
        draw(get(
          s.toString(),
          font,
          color,
          scl,
          if (curr.maxWidth > 0) curr.maxWidth - currOff else Float.MAX_VALUE,
        ))
      }

      if (curr.maxWidth > 0) {
        str.forEach { char ->
          if (char.isWhitespace()) {
            if (nonSpaceNow) {
              nonSpaceNow = false
              splitIndex = currIndex
            }
          }
          else {
            nonSpaceNow = true

            if (currIndex - splitIndex > 20) {
              splitIndex = -1
              nonSpaceNow = false
            }
          }

          builder.append(char)
          currIndex++

          val glyph = data.getGlyph(char)

          if (currOff + glyph.xoffset > curr.offsetX + curr.maxWidth - curr.marginX) {
            if (splitIndex < 0) splitIndex = builder.length - 1

            val p1 = builder.substring(0, splitIndex)
            addStr(p1)

            row(mdStyle.linesPadding)

            builder = StringBuilder(
              if (splitIndex < builder.length) builder.substring(splitIndex).trimStart()
              else ""
            )
            splitIndex = -1
            nonSpaceNow = builder.isNotBlank()
            currIndex = builder.length - 1
            currOff = curr.currOffsetX + builder.sumOf { data.getGlyph(it).xoffset }
          }
          else {
            currOff += glyph.xoffset
          }

          if (builder.isNotBlank()) {
            addStr(builder.trimStart())
          }
        }
      }
      else {
        addStr(str)
      }
    }
  }
}
