package universe.ui.markdown.extensions.table

import arc.scene.ui.layout.Table
import org.commonmark.ext.gfm.tables.*
import org.commonmark.node.CustomBlock
import org.commonmark.node.CustomNode
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

open class TableRenderer(
  context: RendererContext,
  provider: TableProvider
) : LayoutNodeRenderer<TableProvider>(context, provider) {
  private inner class Visitor: NoActionVisitor() {
    override fun visit(customBlock: CustomBlock) {
      if (customBlock is TableBlock) { provider.apply { context.add(customBlock) } }
    }

    override fun visit(customNode: CustomNode) {
      if (customNode is TableHead) { provider.apply { context.add(customNode) } }
      if (customNode is TableBody) { provider.apply { context.add(customNode) } }
      if (customNode is TableRow) { provider.apply { context.add(customNode) } }
      if (customNode is TableCell) { provider.apply { context.add(customNode) } }
      if (customNode is CellShadowBox) { provider.apply { context.add(customNode) } }
    }
  }
  private val visitorInst = Visitor()

  private var currentTable: Table? = null

  override fun getNodeTypes() = setOf(
    TableBlock::class.java,
    TableHead::class.java,
    TableBody::class.java,
    TableRow::class.java,
    TableCell::class.java,
    CellShadowBox::class.java,
  )

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}