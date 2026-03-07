package universe.ui.markdown

import arc.graphics.Color
import arc.graphics.g2d.Font

class TextMirror internal constructor(
  val text: String,
  val font: Font,
  val fontColor: Color,
  val offx: Float,
  val offy: Float,
  val width: Float,
  val height: Float
) {
  var sub: TextMirror? = null
}
