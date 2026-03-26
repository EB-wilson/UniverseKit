package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.graphics.g2d.FontCache
import arc.graphics.g2d.GlyphLayout
import arc.util.Align
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import universe.ui.markdown.Markdown
import universe.ui.markdown.RendererContext

open class DrawStr internal constructor() : Markdown.MarkdownDraw() {
  companion object {
    fun get(
      str: String,
      font: Font,
      color: Color,
      scl: Float,
    ): DrawStr = Pools.obtain(DrawStr::class.java) { DrawStr() }.apply {
      this.text = str
      this.font = font
      this.scl = scl
      this.color = color
    }
  }

  var text: String = ""
  var font: Font = Fonts.def
  var scl: Float = 0f
  var color: Color = Color.white

  private var cache: FontCache? = null
  private var layout: GlyphLayout? = null

  override fun prefWidth(): Float = layout?.width?:0f
  override fun prefHeight(): Float = layout?.height?:0f

  override fun setup(scope: RendererContext.Scope) {
    val data = font.getData()
    val lastScl = data.scaleX
    data.setScale(scl)
    cache = font.newFontCache()
    layout = cache!!.setText(
      text,
      0f, 0f,
      0, text.length,
      0f,
      Align.topLeft,
      false,
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
  }
}
