package universe.ui.markdown.url

import arc.Core
import arc.graphics.*
import arc.graphics.gl.FrameBuffer
import arc.graphics.gl.Shader
import universe.ui.markdown.UrlHandler
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

open class AtlasHandler: UrlHandler {
  private val shader = Shader(
     """
     attribute vec4 a_position;
     attribute vec2 a_texCoord0;
     varying vec2 v_texCoord;
     void main() {
       gl_Position = a_position;
       v_texCoord = a_texCoord0;
     }
     """.trimIndent(),
     """
     varying vec2 v_texCoord;
     uniform sampler2D u_texture;
     void main() {
       gl_FragColor = texture2D(u_texture, v_texCoord);
     }
     """.trimIndent()
  )
  private val mesh = Mesh(false, 4, 0,
                          VertexAttribute.position, VertexAttribute.texCoords)
  private val buffer = FrameBuffer()
  private val pixmapWriter = PixmapIO.PngWriter()

  override fun matchedSchemes() = listOf(
    "atlas"
  )

  override fun openUrl(url: String) {
    throw UnsupportedOperationException("Cannot open a atlas url directly.")
  }

  override fun getResource(url: String): UrlHandler.ResourceHandle {
    val name = url.replaceFirst("atlas:", "")
    val region = Core.atlas.find(name)
    val u = region.u
    val v = region.v
    val u2 = region.u2
    val v2 = region.v2
    val texture = region.texture
    val pixmap = Pixmap(region.width, region.height)
    val pixels = pixmap.pixels

    val vertices = floatArrayOf(
      -1f,-1f, u, v,
      1f, -1f, u2, v,
      1f,  1f, u2, v2,
      -1f, 1f, u, v2,
    )
    mesh.setVertices(vertices)

    buffer.resize(region.width, region.height)
    buffer.begin(Color.clear)
    shader.bind()
    shader.setUniformi("u_texture", 0)
    texture.bind()
    mesh.render(shader, Gl.triangleFan)
    Gl.readPixels(0, 0, region.width, region.height, Gl.rgba, Gl.unsignedByte, pixels)
    buffer.end()

    val res = ByteArrayOutputStream()
    pixmapWriter.write(res, pixmap.flipY())

    return UrlHandler.ByteArrayHandle(res.toByteArray())
  }
}