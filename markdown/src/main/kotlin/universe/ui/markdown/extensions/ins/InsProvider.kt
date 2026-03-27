package universe.ui.markdown.extensions.ins

import org.commonmark.ext.ins.Ins
import universe.ui.markdown.MarkdownProvider
import universe.ui.markdown.RendererContext

interface InsProvider: MarkdownProvider {
  fun RendererContext.add(node: Ins)
}