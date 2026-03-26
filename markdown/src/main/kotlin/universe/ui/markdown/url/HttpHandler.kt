package universe.ui.markdown.url

import arc.Core
import arc.func.Cons2
import arc.util.Http
import universe.ui.markdown.UrlHandler
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

class HttpHandler: UrlHandler {

  override fun matchedSchemes() = listOf(
    "https",
    "http"
  )

  override fun openUrl(url: String) {
    Core.app.openURI(url)
  }

  override fun getResource(url: String) = HttpsHandle(URI(url).toURL())

  class HttpsHandle(
    val url: URL,
    val timeout: Int = 2000,
  ): UrlHandler.ResourceHandle(){
    private var currentConnection: HttpURLConnection? = null

    override fun openStream(): InputStream {
      val connection = url.openConnection() as HttpURLConnection
      currentConnection = connection

      connection.setDoOutput(false)
      connection.setDoInput(true)
      connection.setRequestMethod("GET")
      HttpURLConnection.setFollowRedirects(true)

      connection.setConnectTimeout(timeout)
      connection.setReadTimeout(timeout)

      connection.connect()

      val code = connection.getResponseCode()

      if (code >= 400) {
        val status = Http.HttpStatus.byCode(code)
        throw ConnectFailedException(
          "HTTP request failed with error: $code ($status, URL = $url)",
          status
        )
      }
      else {
        return connection.getInputStream()
      }
    }

    override fun close() {
      super.close()
      currentConnection!!.disconnect()
    }
  }

  private class ConnectFailedException(
    override val message: String,
    val code: Http.HttpStatus,
  ) : Exception()
}