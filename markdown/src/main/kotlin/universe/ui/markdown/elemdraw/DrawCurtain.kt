package universe.ui.markdown.elemdraw

import arc.scene.Element
import arc.scene.actions.Actions
import arc.scene.style.Drawable
import arc.scene.ui.Image
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.ActivityDrawer
import universe.ui.markdown.Markdown.DrawObj

class DrawCurtain internal constructor() : DrawObj(), ActivityDrawer {
  var width: Float = 0f
  var height: Float = 0f
  var drawable: Drawable? = null

  var image: Image? = null

  override fun draw() {}

  override val elem: Element get() = image!!

  override fun width(): Float {
    return width
  }

  override fun height(): Float {
    return height
  }

  companion object {
    fun get(owner: Markdown, drawable: Drawable, offX: Float, offY: Float, width: Float, height: Float): DrawCurtain {
      val res = Pools.obtain(DrawCurtain::class.java) { DrawCurtain() }
      res.parent = owner
      res.drawable = drawable
      res.offsetX = offX
      res.offsetY = offY
      res.width = width
      res.height = height

      res.image = object : Image(drawable) {
        var alp: Boolean = false

        init {
          setSize(width, height)

          clicked {
            alp = !alp
            clearActions()
            actions(Actions.alpha(if (alp) 0.2f else 1f, 0.3f))
          }

          hovered {
            if (alp) return@hovered
            clearActions()
            actions(Actions.alpha(0.2f, 0.3f))
          }

          exited {
            if (alp) return@exited
            clearActions()
            actions(Actions.alpha(1f, 0.3f))
          }
        }
      }

      return res
    }
  }
}
