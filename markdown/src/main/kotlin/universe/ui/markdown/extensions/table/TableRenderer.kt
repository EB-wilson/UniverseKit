package universe.ui.markdown.extensions.table

import arc.scene.ui.layout.Table
import org.commonmark.ext.gfm.tables.*
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

open class TableRenderer(
  context: RendererContext,
  provider: TableProvider
) : LayoutNodeRenderer<TableProvider>(context, provider) {
  private inner class Visitor: NoActionVisitor(), TableVisitor {
    override fun visit(block: TableBlock) { provider.apply { context.add(block) } }
    override fun visit(head: TableHead) { provider.apply { context.add(head) } }
    override fun visit(body: TableBody) { provider.apply { context.add(body) } }
    override fun visit(row: TableRow) { provider.apply { context.add(row) } }
    override fun visit(cell: TableCell) { provider.apply { context.add(cell) } }
  }
  private val visitorInst = Visitor()

  private var currentTable: Table? = null

  override fun getNodeTypes() = setOf(
    TableBlock::class.java,
    TableHead::class.java,
    TableBody::class.java,
    TableRow::class.java,
    TableCell::class.java
  )

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}