package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.graphics.g2d.FontCache
import arc.graphics.g2d.GlyphLayout
import arc.scene.style.Drawable
import arc.util.Align
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.DrawObj

open class DrawStr  //use get
internal constructor() : DrawObj() {
  var text: String? = null
  var font: Font? = null
  var scl: Float = 0f
  var color: Color? = null
  var drawable: Drawable? = null

  private var cache: FontCache? = null
  private var layout: GlyphLayout? = null

  override fun draw() {
    cache!!.tint(tmp1.set(color).mul(Draw.getColor()))
    parent?.let { parent ->
      cache!!.setPosition(
        parent.x + offsetX,
        parent.y + parent.getHeight() + offsetY
      )

      if (drawable != null) {
        tmp2.set(Draw.getColor())

        drawable!!.draw(
          parent.x + offsetX - drawable!!.leftWidth,
          parent.y + parent.getHeight() + offsetY - font!!.lineHeight - 2,
          layout!!.width + drawable!!.leftWidth + drawable!!.rightWidth,
          font!!.lineHeight + 5
        )

        Draw.color(tmp2)
      }
    }

    cache!!.draw()
  }

  override fun reset() {
    super.reset()
    text = null
    font = null
    scl = 0f
    color = null
    drawable = null
  }

  companion object {
    fun get(
      owner: Markdown,
      str: String,
      font: Font,
      color: Color,
      ox: Float,
      oy: Float,
      scl: Float,
      background: Drawable?
    ): DrawStr {
      val res = Pools.obtain(DrawStr::class.java, Prov { DrawStr() })
      res.parent = owner
      res.text = str
      res.font = font
      res.offsetX = ox
      res.offsetY = oy
      res.scl = scl
      res.color = color
      res.drawable = background

      val data = font.getData()
      val lastScl = data.scaleX
      data.setScale(scl)
      res.cache = font.newFontCache()
      res.layout = res.cache!!.setText(
        str,
        0f, 0f,
        0f,
        Align.topLeft,
        false
      )
      data.setScale(lastScl)

      return res
    }
  }
}
