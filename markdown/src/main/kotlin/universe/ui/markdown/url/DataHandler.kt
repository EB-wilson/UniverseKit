package universe.ui.markdown.url

import universe.ui.markdown.UrlHandler
import java.nio.charset.Charset
import java.util.*

open class DataHandler: UrlHandler {
  companion object {
    private val MIME_TYPE_PATTERN = "\\w+(/\\w+)?;".toRegex()
    private val DATA_TYPE_PATTERN = "\\w+,".toRegex()
  }

  override fun matchedSchemes(): List<String> = listOf(
    "data"
  )

  override fun openUrl(url: String) {
    throw UnsupportedOperationException("Cannot open a data url directly.")
  }

  override fun getResource(url: String): UrlHandler.ResourceHandle {
    val url = url.replaceFirst("data:", "")
    val mimeMatch = MIME_TYPE_PATTERN.matchAt(url, 0)
    val mimeEnd = mimeMatch?.range?.let { it.last + 1 }?: 0
    val dataMatch = DATA_TYPE_PATTERN.matchAt(url, mimeEnd)
    val dataEnd = dataMatch?.range?.let { it.last + 1 }?: mimeEnd

    val dataType = dataMatch?.value
    if (dataType == "base64,") {
      val base64 = url.substring(dataEnd)

      return Base64Handle(base64)
    }
    else {
      val string = url.substring(dataEnd)

      return StringHandle(string)
    }
  }

  class Base64Handle(
    val base64: String
  ): UrlHandler.ResourceHandle() {
    override fun openStream() = Base64.getDecoder().wrap(base64.byteInputStream())
  }

  class StringHandle(
    val string: String,
    val charset: Charset = Charsets.UTF_8
  ): UrlHandler.ResourceHandle() {
    override fun openStream() = string.byteInputStream(charset)
  }
}