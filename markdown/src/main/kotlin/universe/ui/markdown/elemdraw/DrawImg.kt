package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Font
import arc.graphics.g2d.TextureRegion
import arc.util.Align
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.DrawObj
import kotlin.math.min

class DrawImg internal constructor() : DrawObj() {
  var title: String? = null
  var titleFont: Font? = null
  var titleColor: Color? = null
  var region: TextureRegion? = null

  override fun reset() {
    super.reset()
    title = null
    titleFont = null
    titleColor = null
    region = null
  }

  override fun draw() {
    parent?.let { parent ->
      val w = min(parent.getWidth(), region!!.width.toFloat())
      val h = region!!.height*(w/region!!.width)

      Draw.rect(region, parent.x + w/2, parent.y + parent.getHeight() + offsetY - h/2, w, h)
      if (title != null) titleFont!!.draw(
        title,
        parent.x + w/2,
        parent.y + parent.getHeight() + offsetY - h - 4,
        titleColor,
        1f,
        true,
        Align.center
      )
    }
  }

  companion object {
    fun get(
      owner: Markdown,
      region: TextureRegion,
      title: String,
      offY: Float,
      titleFont: Font,
      titleColor: Color
    ): DrawImg {
      val res = Pools.obtain(DrawImg::class.java) { DrawImg() }
      res.parent = owner
      res.title = title
      res.region = region
      res.offsetX = 0f
      res.offsetY = offY
      res.titleFont = titleFont
      res.titleColor = titleColor

      return res
    }
  }
}
