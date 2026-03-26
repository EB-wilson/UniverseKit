package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.style.Drawable
import arc.scene.ui.Image
import arc.util.pooling.Pools
import mindustry.gen.Tex
import universe.ui.markdown.Markdown
import universe.ui.markdown.RendererContext

open class DrawCurtain: DrawStr(), Markdown.ActivityDrawer {
  companion object {
    fun get(
      curtainDraw: Drawable,
      str: String,
      font: Font,
      color: Color,
      scl: Float,
      growthLeft: Float,
      growthRight: Float,
      growthTop: Float,
      growthBottom: Float
    ): DrawCurtain = Pools.obtain(DrawCurtain::class.java){ DrawCurtain() }.apply {
      this.curtainDraw = curtainDraw
      this.text = str
      this.font = font
      this.scl = scl
      this.color = color
      this.growthLeft = growthLeft
      this.growthRight = growthRight
      this.growthTop = growthTop
      this.growthBottom = growthBottom
    }
  }

  var curtainDraw: Drawable = Tex.nomap
  var growthLeft = 0f
  var growthRight = 0f
  var growthTop = 0f
  var growthBottom = 0f

  private lateinit var curtain: Image

  override fun reset() {
    super.reset()
    growthLeft = 0f
    growthRight = 0f
    growthTop = 0f
    growthBottom = 0f
  }

  override fun prefWidth(): Float = super.prefWidth() + growthLeft + growthRight
  override fun prefHeight(): Float = super.prefHeight() + growthTop + growthBottom

  override fun setup(scope: RendererContext.Scope) {
    super.setup(scope)

    curtain = Image(curtainDraw)
  }

  override fun draw(x: Float, y: Float) {
    super.draw(x + growthLeft, y - growthTop)
  }

  override fun getActiveElement(): Element = curtain
}