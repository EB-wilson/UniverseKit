package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.scene.style.Drawable
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.DrawObj

class DrawBoard internal constructor() : DrawObj() {
  var height: Float = 0f
  var drawable: Drawable? = null
  var lay: Int = 0

  override fun priority(): Int {
    return if (lay == 0) 0 else -(1000 - lay)
  }

  override fun reset() {
    super.reset()
    parent = null
    drawable = null
    height = 0f
    lay = 0
  }

  override fun draw() {
    parent?.let { parent ->
      drawable!!.draw(
        parent.x + offsetX,
        parent.y + parent.getHeight() + offsetY - height,
        parent.getWidth() - offsetX,
        height
      )
    }
  }

  companion object {
    fun get(owner: Markdown, drawable: Drawable, layer: Int, height: Float, offX: Float, offY: Float): DrawBoard {
      val res = Pools.obtain(DrawBoard::class.java, Prov { DrawBoard() })
      res.parent = owner
      res.drawable = drawable
      res.height = height
      res.offsetX = offX
      res.offsetY = offY
      res.lay = layer

      return res
    }
  }
}
