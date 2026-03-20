package universe.ui.markdown.extensions.curtain

import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

class CurtainRenderer(
  context: RendererContext,
  provider: CurtainProvider,
) : LayoutNodeRenderer<CurtainProvider>(context, provider) {
  private inner class Visitor: NoActionVisitor(), CurtainVisitor {
    override fun visit(curtain: Curtain) { provider.apply { context.add(curtain) } }
  }
  private val visitorInst = Visitor()

  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Curtain::class.java)
  }

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}