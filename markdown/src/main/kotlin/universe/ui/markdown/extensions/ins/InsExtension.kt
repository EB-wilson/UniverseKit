package universe.ui.markdown.extensions.ins

import org.commonmark.Extension
import org.commonmark.ext.ins.internal.InsDelimiterProcessor
import org.commonmark.parser.Parser
import universe.ui.markdown.MDLayoutRenderer
import universe.ui.markdown.extensions.curtain.CurtainRenderer

class InsExtension private constructor() : Parser.ParserExtension, MDLayoutRenderer.DrawRendererExtension {
  companion object {
    fun create(): Extension = InsExtension()
  }

  override fun extend(parserBuilder: Parser.Builder) {
    parserBuilder.customDelimiterProcessor(InsDelimiterProcessor())
  }

  override fun extend(rendererBuilder: MDLayoutRenderer.Builder) {
    rendererBuilder.nodeRendererFactory(::InsNodeRenderer)
  }

}