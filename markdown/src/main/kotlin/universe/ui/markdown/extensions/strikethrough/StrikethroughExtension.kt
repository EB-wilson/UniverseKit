package universe.ui.markdown.extensions.strikethrough

import org.commonmark.Extension
import org.commonmark.ext.gfm.strikethrough.internal.StrikethroughDelimiterProcessor
import org.commonmark.parser.Parser
import universe.ui.markdown.MDLayoutRenderer

class StrikethroughExtension private constructor() : Parser.ParserExtension, MDLayoutRenderer.DrawRendererExtension {
  companion object {
    fun create(): Extension = StrikethroughExtension()
  }

  override fun extend(parserBuilder: Parser.Builder) {
    parserBuilder.customDelimiterProcessor(StrikethroughDelimiterProcessor())
  }

  override fun extend(rendererBuilder: MDLayoutRenderer.Builder) {
    rendererBuilder.nodeRendererFactory(::StrikethroughRenderer)
  }

}