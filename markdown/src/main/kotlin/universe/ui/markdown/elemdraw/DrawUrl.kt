package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.Element
import arc.scene.ui.TextButton
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import mindustry.ui.Styles
import universe.ui.markdown.Markdown
import universe.ui.markdown.RendererContext
import universe.ui.markdown.UrlClickedEvent

open class DrawUrl: Markdown.MarkdownDraw(), Markdown.ActivityDrawer {
  companion object {
    fun get(
      str: String,
      url: String,
      font: Font,
      color: Color,
      scl: Float,
      overColor: Color = color,
    ): DrawUrl = Pools.obtain(DrawUrl::class.java) { DrawUrl() }.apply {
      this.text = str
      this.url = url
      this.font = font
      this.color = color
      this.scl = scl
      this.overColor = overColor
    }
  }

  var text: String = ""
  var url: String = ""
  var font: Font = Fonts.def
  var color: Color = Color.white
  var scl: Float = 0f
  var overColor: Color = color

  internal lateinit var button: TextButton

  override val activeElement: Element get() = button

  override fun reset() {
    super.reset()
    text = ""
    url = ""
    font = Fonts.def
    color = Color.white
    scl = 0f
    overColor = Color.white
  }

  override fun prefWidth(): Float = button.width
  override fun prefHeight(): Float = button.height

  override fun setup(scope: RendererContext.Scope) {
    button = TextButton(text, makeStyle())
    button.label.setWrap(false)
    button.label.setFontScale(scl)
    button.clicked { button.fire(UrlClickedEvent(url)) }
    button.pack()
  }

  private fun makeStyle() = TextButton.TextButtonStyle().apply {
    font = this@DrawUrl.font
    fontColor = color
    overFontColor = overColor
    up = Styles.none
  }

  override fun draw(x: Float, y: Float) {}
}