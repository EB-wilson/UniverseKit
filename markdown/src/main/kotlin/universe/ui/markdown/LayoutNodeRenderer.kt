package universe.ui.markdown

import org.commonmark.renderer.NodeRenderer

abstract class LayoutNodeRenderer<P: MarkdownProvider>(
  protected val context: RendererContext,
  protected val provider: P
) : NodeRenderer {

}
