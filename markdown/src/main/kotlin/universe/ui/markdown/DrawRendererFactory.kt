package universe.ui.markdown

import org.commonmark.renderer.NodeRenderer

fun interface DrawRendererFactory<P: MarkdownProvider> {
  fun create(
    context: RendererContext,
    provider: P,
  ): NodeRenderer
}
