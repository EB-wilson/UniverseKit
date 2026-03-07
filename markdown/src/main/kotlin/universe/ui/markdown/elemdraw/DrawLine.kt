package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.graphics.Color
import arc.graphics.g2d.Lines
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.DrawObj

class DrawLine internal constructor() : DrawObj() {
  var width: Float = 0f
  var color: Color? = null

  override fun priority(): Int {
    return 1
  }

  override fun reset() {
    super.reset()
    parent = null
    color = null
    width = 0f
  }

  override fun draw() {
    Lines.stroke(2f, color)
    parent?.let { parent ->
      Lines.line(
        parent.x + offsetX, parent.y + parent.getHeight() + offsetY,
        parent.x + offsetX + width, parent.y + parent.getHeight() + offsetY
      )
    }
  }

  companion object {
    fun get(owner: Markdown, color: Color, offX: Float, offY: Float, width: Float): DrawLine {
      val res = Pools.obtain(DrawLine::class.java) { DrawLine() }
      res.parent = owner
      res.color = color
      res.offsetX = offX
      res.offsetY = offY
      res.width = width

      return res
    }
  }
}
