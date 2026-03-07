package universe.ui.markdown.extensions.curtain

import mindustry.gen.Tex
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.RendererContext
import universe.ui.markdown.elemdraw.DrawCurtain

class CurtainRenderer(
  context: RendererContext,
  provider: CurtainProvider,
) : LayoutNodeRenderer<CurtainProvider>(context, provider) {
  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Curtain::class.java)
  }

  override fun render(node: Node) {
    val style = context.style

    val cur = style.curtain ?: Tex.whiteui
    context.lastText = null
    val begin = context.rendOff
    context.rendOff += cur.leftWidth

    visitChildren(node)

    context.rendOff += cur.rightWidth
    context.draw(
      DrawCurtain.Companion.get(
        context.element, style.curtain!!, begin, context.lastText?.offy?: 0f,
        (context.lastText?.width?: 0f) + cur.leftWidth + cur.rightWidth,
        (context.lastText?.height?: 0f) + cur.topHeight + cur.bottomHeight
      )
    )
  }
}