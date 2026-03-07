package universe.ui.markdown.extensions.curtain

import org.commonmark.node.Document
import universe.ui.markdown.MarkdownProvider
import universe.ui.markdown.RendererContext

interface CurtainProvider: MarkdownProvider {
  fun RendererContext.add(node: Curtain)
}