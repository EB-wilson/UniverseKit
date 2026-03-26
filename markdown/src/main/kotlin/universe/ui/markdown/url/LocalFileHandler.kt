package universe.ui.markdown.url

import arc.Core
import universe.ui.markdown.UrlHandler
import java.io.File

open class LocalFileHandler: UrlHandler {
  override fun matchedSchemes() = listOf(
    "file"
  )

  override fun openUrl(url: String) {
    val fi = url.replaceFirst("file://", "")
    Core.app.openFolder(fi)
  }

  override fun getResource(url: String): UrlHandler.ResourceHandle {
    val fi = url.replaceFirst("file://", "")
    val file = File(fi)
    return UrlHandler.FileHandle(file)
  }
}