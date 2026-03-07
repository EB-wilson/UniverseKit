package universe.ui.markdown.extensions.table

import org.commonmark.Extension
import org.commonmark.ext.gfm.tables.internal.TableBlockParser
import org.commonmark.parser.Parser
import universe.ui.markdown.MDLayoutRenderer

class TablesExtension private constructor() : Parser.ParserExtension, MDLayoutRenderer.DrawRendererExtension {
  companion object {
    fun create(): Extension = TablesExtension()
  }

  override fun extend(parserBuilder: Parser.Builder) {
    parserBuilder.customBlockParserFactory(TableBlockParser.Factory())
  }

  override fun extend(rendererBuilder: MDLayoutRenderer.Builder) {
    rendererBuilder.nodeRendererFactory(::TableRenderer)
  }

}