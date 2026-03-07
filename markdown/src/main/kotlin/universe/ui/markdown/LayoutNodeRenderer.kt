package universe.ui.markdown

import org.commonmark.node.Node
import org.commonmark.renderer.NodeRenderer

abstract class LayoutNodeRenderer<P: MarkdownProvider>(
  protected val context: RendererContext,
  protected val provider: P
) : NodeRenderer {
  protected open fun visitChildren(node: Node) {
    var n = node.firstChild
    while (n != null) {
      val next = n.next
      context.render(n)
      n = next
    }
  }
}
