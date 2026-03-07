package universe.ui.markdown.elemdraw

import arc.func.Prov
import arc.scene.Element
import arc.scene.ui.ScrollPane
import arc.scene.ui.layout.Table
import arc.util.pooling.Pools
import mindustry.ui.Styles
import universe.ui.markdown.Markdown
import universe.ui.markdown.Markdown.ActivityDrawer
import universe.ui.markdown.Markdown.DrawObj
import kotlin.math.min

class DrawTable internal constructor() : DrawObj(), ActivityDrawer {
  var table: Table? = null
  var pane: ScrollPane? = null

  override fun draw() {}

  override val elem: Element get() = pane!!

  override fun width(): Float {
    return min(parent?.getWidth()?:Float.POSITIVE_INFINITY, table!!.prefWidth)
  }

  override fun height(): Float {
    return table!!.prefHeight
  }

  override fun reset() {
    super.reset()
    table = null
    pane = null
  }

  companion object {
    fun get(owner: Markdown, table: Table, ox: Float, oy: Float): DrawTable {
      val res = Pools.obtain(DrawTable::class.java) { DrawTable() }
      res.parent = owner
      res.table = table
      res.pane = ScrollPane(table, Styles.noBarPane)
      res.offsetX = ox
      res.offsetY = oy

      return res
    }
  }
}
