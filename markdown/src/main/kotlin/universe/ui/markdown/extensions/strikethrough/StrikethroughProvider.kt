package universe.ui.markdown.extensions.strikethrough

import org.commonmark.ext.gfm.strikethrough.Strikethrough
import universe.ui.markdown.MarkdownProvider
import universe.ui.markdown.RendererContext

interface StrikethroughProvider: MarkdownProvider {
  fun RendererContext.add(node: Strikethrough)
}