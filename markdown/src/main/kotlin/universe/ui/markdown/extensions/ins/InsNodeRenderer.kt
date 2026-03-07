package universe.ui.markdown.extensions.ins

import org.commonmark.ext.ins.Ins
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.RendererContext
import universe.ui.markdown.elemdraw.DrawLine

class InsNodeRenderer(
  element: RendererContext,
  provider: InsProvider
) : LayoutNodeRenderer<InsProvider>(element, provider) {
  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Ins::class.java)
  }

  override fun render(node: Node) {
    context.lastText = null

    visitChildren(node)

    var orig = context.lastText
    while (orig != null) {
      context.draw(
        DrawLine.Companion.get(
          context.element,
          orig.fontColor,
          orig.offx,
          orig.offy - orig.height,
          orig.width
        )
      )

      orig = orig.sub
    }
  }
}