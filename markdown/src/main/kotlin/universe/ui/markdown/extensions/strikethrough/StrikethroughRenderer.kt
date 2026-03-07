package universe.ui.markdown.extensions.strikethrough

import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.RendererContext
import universe.ui.markdown.elemdraw.DrawLine

class StrikethroughRenderer(
  context: RendererContext,
  provider: StrikethroughProvider,
) : LayoutNodeRenderer<StrikethroughProvider>(context, provider) {
  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Strikethrough::class.java)
  }

  override fun render(node: Node) {
    context.lastText = null
    visitChildren(node)
    var orig = context.lastText
    while (orig != null) {
      context.draw(
        DrawLine.get(
          context.element,
          orig.fontColor,
          orig.offx,
          orig.offy - orig.height/2,
          orig.width
        )
      )

      orig = orig.sub
    }
  }
}