package universe.ui.markdown.url

import universe.ui.markdown.Markdown
import universe.ui.markdown.UrlHandler
import java.io.InputStream
import java.nio.file.NoSuchFileException

open class ResourceHandler: UrlHandler {
  override fun matchedSchemes(): List<String> = listOf(
    "resource"
  )

  override fun openUrl(url: String) {
    throw UnsupportedOperationException("open jar resource was not supported")
  }

  override fun getResource(url: String): UrlHandler.ResourceHandle {
    val path = url.replaceFirst("resource://", "")
    return object: UrlHandler.ResourceHandle(){
      override fun openStream(): InputStream = Markdown::class.java.classLoader.getResourceAsStream(path)?:
        throw NoSuchFileException("no such jar resource found in $path")
    }
  }
}