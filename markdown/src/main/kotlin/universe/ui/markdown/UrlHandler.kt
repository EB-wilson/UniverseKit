package universe.ui.markdown

import java.io.ByteArrayInputStream
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

interface UrlHandler {
  fun matchedSchemes(): List<String>

  fun openUrl(url: String)
  fun getResource(url: String): ResourceHandle

  abstract class ResourceHandle: Closeable{
    private var currStream: InputStream? = null

    open fun open(): InputStream{
      if (currStream != null)
        throw IllegalStateException("Can't open a handle twice at same time")
      currStream = openStream()

      return currStream!!
    }

    override fun close(){
      currStream?.close()
      currStream = null
    }

    internal abstract fun openStream(): InputStream
  }

  class ByteArrayHandle(
    private val bytes: ByteArray,
  ): ResourceHandle() {
    override fun openStream() = ByteArrayInputStream(bytes)
  }

  class FileHandle(
    private val file: File,
  ): ResourceHandle() {
    override fun openStream() = FileInputStream(file)
  }
}