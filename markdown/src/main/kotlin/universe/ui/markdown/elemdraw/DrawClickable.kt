package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.ui.TextButton
import arc.scene.ui.Tooltip
import arc.util.pooling.Pools
import mindustry.ui.Styles
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.ActivityDrawer

class DrawClickable internal constructor() : DrawStr(), ActivityDrawer {
  var openUrl: TextButton? = null

  override fun draw() {
  }

  override val elem: Element get() = openUrl!!

  override fun width(): Float {
    return openUrl!!.getLabel().prefWidth
  }

  override fun height(): Float {
    return openUrl!!.getLabel().prefHeight
  }

  override fun reset() {
    super.reset()
    openUrl = null
  }

  companion object {
    fun get(
      owner: Markdown,
      str: String,
      strFont: Font,
      clicked: Runnable,
      tooltip: Tooltip?,
      color: Color,
      ox: Float,
      oy: Float,
      scl: Float
    ): DrawClickable {
      val res = Pools.obtain(DrawClickable::class.java) { DrawClickable() }
      res.parent = owner
      res.text = str
      res.openUrl = object : TextButton(str, object : TextButtonStyle(Styles.nonet) {
        init {
          fontColor = color
          font = strFont
        }
      }) {
        init {
          clicked(clicked)
          label.setScale(scl)
          label.setWrap(false)

          addListener(tooltip)
        }
      }
      res.offsetX = ox
      res.offsetY = oy
      res.scl = scl
      res.color = color

      return res
    }
  }
}
