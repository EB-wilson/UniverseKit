package universe.ui.markdown.extensions.strikethrough

import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

class StrikethroughRenderer(
  context: RendererContext,
  provider: StrikethroughProvider,
) : LayoutNodeRenderer<StrikethroughProvider>(context, provider) {
  private inner class Visitor: NoActionVisitor(), StrikethroughVisitor {
    override fun visit(strikethrough: Strikethrough) { provider.apply { context.add(strikethrough) } }
  }
  private val visitorInst = Visitor()

  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Strikethrough::class.java)
  }

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}