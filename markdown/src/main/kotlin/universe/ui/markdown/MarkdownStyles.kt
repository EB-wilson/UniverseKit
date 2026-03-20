package universe.ui.markdown

import arc.Core
import arc.Files
import arc.files.Fi
import arc.freetype.FreeTypeFontGenerator
import arc.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import arc.graphics.Color
import arc.graphics.g2d.Fill
import arc.graphics.g2d.Font
import arc.graphics.g2d.Lines
import arc.scene.style.BaseDrawable
import arc.scene.style.Drawable
import arc.scene.style.TextureRegionDrawable
import arc.scene.ui.layout.Scl
import arc.util.Log
import arc.util.Tmp
import mindustry.Vars
import mindustry.gen.Tex
import mindustry.graphics.Pal
import mindustry.ui.Fonts
import mindustry.ui.Styles
import universe.ui.markdown.Markdown.MarkdownStyle
import java.io.File
import java.io.IOException
import java.io.InputStream

object MarkdownStyles {
  /**默认Markdown文档风格记录对象，等宽字体来自JetBrains IDEs的Mono字体，十分感谢 */
  val defaultMD: MarkdownStyle

  init {
    var temp: MarkdownStyle
    try {
      MarkdownStyles::class.java.getClassLoader().getResource("fonts/JetBrainsMono.ttf").also { url ->
        val fi = object: Fi(url!!.file, Files.FileType.classpath){
          override fun read(): InputStream = url!!.openStream()
        }

        val gen = FreeTypeFontGenerator(fi)
        temp = makeDefault(gen.generateFont(object : FreeTypeFontParameter() {
          init {
            size = Scl.scl(19f).toInt()
            borderWidth = Scl.scl(0.3f)
            shadowOffsetY = 2
            incremental = true
            borderColor = color
          }
        }))
      }
    } catch (e: IOException) {
      Log.err(e)
      temp = makeDefault(Fonts.def)
    }

    defaultMD = temp
  }

  private fun makeDefault(mono: Font): MarkdownStyle {
    return object : MarkdownStyle() {
      init {
        subFont = Fonts.def
        font = subFont
        codeFont = mono
        val gen = FreeTypeFontGenerator(Core.files.internal("fonts/font.woff"))
        strongFont = gen.generateFont(object : FreeTypeFontParameter() {
          init {
            size = Scl.scl(19f).toInt()
            borderWidth = Scl.scl(0.3f)
            shadowOffsetY = 2
            incremental = true
            borderColor = color
          }
        })
        emFont = Fonts.def

        textColor = Color.white
        emColor = Pal.accent
        subTextColor = Color.lightGray
        lineColor = Color.gray
        linkColor = Pal.place

        linesPadding = 6f
        paragraphPadding = 20f
        tablePadHor = 16f
        tablePadVert = 12f

        board = Tex.paneLeft
        codeBack = (Tex.whiteui as TextureRegionDrawable).tint(Tmp.c1.set(Pal.darkerGray).a(0.7f))
        codeBack.leftWidth = 4f
        codeBack.rightWidth = 4f
        codeBlockBack = (Tex.whiteui as TextureRegionDrawable).tint(Tmp.c1.set(Pal.darkerGray))
        codeBlockStyle = Styles.smallPane

        tableBack1 = (Tex.whiteui as TextureRegionDrawable).tint(Tmp.c1.set(Pal.darkerGray).a(0.7f))
        tableBack2 = (Tex.whiteui as TextureRegionDrawable).tint(Tmp.c1.set(Color.gray).a(0.7f))

        curtain = (Tex.whiteui as TextureRegionDrawable).tint(Pal.darkerGray)

        listMarks = arrayOf<Drawable>(
          object : BaseDrawable() {
            override fun draw(x: Float, y: Float, width: Float, height: Float) {
              Fill.square(x + width/2, y + height/2, width*0.25f, 45f)
            }
          },
          object : BaseDrawable() {
            override fun draw(x: Float, y: Float, width: Float, height: Float) {
              Fill.circle(x + width/2, y + height/2, width*0.3f)
            }
          },
          object : BaseDrawable() {
            override fun draw(x: Float, y: Float, width: Float, height: Float) {
              Lines.stroke(1f)
              Lines.circle(x + width/2, y + height/2, width*0.3f)
            }
          }
        )
      }
    }
  }
}
