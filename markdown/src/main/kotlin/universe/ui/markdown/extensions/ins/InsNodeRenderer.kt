package universe.ui.markdown.extensions.ins

import org.commonmark.ext.ins.Ins
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

class InsNodeRenderer(
  element: RendererContext,
  provider: InsProvider
) : LayoutNodeRenderer<InsProvider>(element, provider) {
  private inner class Visitor: NoActionVisitor(), InsVisitor {
    override fun visit(ins: Ins) { provider.apply { context.add(ins) } }
  }
  private val visitorInst = Visitor()

  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Ins::class.java)
  }

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}