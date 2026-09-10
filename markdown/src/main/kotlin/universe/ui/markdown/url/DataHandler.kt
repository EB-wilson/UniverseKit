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
    val noScheme = url.replaceFirst("data:", "")
    val comma = noScheme.indexOf(',')
    if (comma < 0) {
      return StringHandle("")
    }

    val header = noScheme.take(comma)
    val payload = noScheme.substring(comma + 1)

    val isBase64 = header.split(';').any { it.equals("base64", ignoreCase = true) }

    return if (isBase64) {
      Base64Handle(payload)
    } else {
      StringHandle(payload)
    }
  }

  class Base64Handle(
    val payload: String
  ): UrlHandler.ResourceHandle() {
    override fun openStream() = Base64.getDecoder().wrap(payload.trim().byteInputStream())
  }

  class StringHandle(
    val string: String,
    val charset: Charset = Charsets.UTF_8
  ): UrlHandler.ResourceHandle() {
    override fun openStream() = string.byteInputStream(charset)
  }
}