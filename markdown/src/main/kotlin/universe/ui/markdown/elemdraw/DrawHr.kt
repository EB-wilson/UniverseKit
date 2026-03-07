package universe.ui.markdown.elemdraw

import arc.Core
import arc.func.Prov
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.DrawObj

class DrawHr internal constructor() : DrawObj() {
  var color: Color? = null

  override fun reset() {
    super.reset()
    color = null
  }

  override fun draw() {
    parent?.let { parent ->
      Draw.color(tmp1.set(color).mul(Draw.getColor()))
      Draw.rect(
        Core.atlas.white(),
        parent.x + parent.getWidth()/2,
        parent.y + parent.getHeight() + offsetY,
        parent.getWidth(),
        4f
      )
    }
  }

  companion object {
    fun get(owner: Markdown, color: Color, offY: Float): DrawHr {
      val res = Pools.obtain(DrawHr::class.java) { DrawHr() }
      res.parent = owner
      res.color = color
      res.offsetX = 0f
      res.offsetY = offY

      return res
    }
  }
}
