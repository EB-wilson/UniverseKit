package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.ui.Label
import arc.scene.ui.Label.LabelStyle
import arc.scene.ui.ScrollPane
import arc.scene.ui.ScrollPane.ScrollPaneStyle
import arc.util.pooling.Pools
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.ActivityDrawer
import universe.ui.markdown.Markdown.DrawObj

class DrawCode internal constructor() : DrawObj(), ActivityDrawer {
  var pane: ScrollPane? = null
  var label: Label? = null

  override fun draw() {}

  override fun reset() {
    super.reset()
    label = null
    pane = null
  }

  override val elem: Element get() = pane!!

  override fun width(): Float {
    return parent!!.getWidth() - offsetX - 12
  }

  override fun height(): Float {
    return label!!.getHeight()
  }

  companion object {
    fun get(
      owner: Markdown,
      language: String,
      code: String,
      textFont: Font,
      ox: Float,
      oy: Float,
      paneStyle: ScrollPaneStyle
    ): DrawCode {
      val res = Pools.obtain(DrawCode::class.java) { DrawCode() }
      res.parent = owner
      res.offsetX = ox
      res.offsetY = oy

      val data = textFont.getData()
      val lastScl = data.scaleX
      data.setScale(1f)

      data.setScale(lastScl)

      textFont.getData().markupEnabled = true
      res.label = Label(code, object : LabelStyle() {
        init {
          font = textFont
        }
      })
      res.pane = ScrollPane(res.label, paneStyle)
      res.label!!.validate()

      return res
    }
  }
}
