package universe.ui.markdown.extensions.table

import arc.scene.ui.layout.Table
import arc.util.Align
import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableCell
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableRow
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.Markdown
import universe.ui.markdown.RendererContext
import universe.ui.markdown.elemdraw.DrawTable

open class TableRenderer(
  context: RendererContext,
  provider: TableProvider
) : LayoutNodeRenderer<TableProvider>(context, provider) {
  private var currentTable: Table? = null

  override fun getNodeTypes() = setOf(
    TableBlock::class.java,
    TableHead::class.java,
    TableBody::class.java,
    TableRow::class.java,
    TableCell::class.java
  )

  override fun render(node: Node) {
    when (node) {
      is TableBlock -> renderBlock(node)
      is TableHead -> renderHead(node)
      is TableBody -> renderBody(node)
      is TableRow -> renderRow(node)
      is TableCell -> renderCell(node)
    }
  }

  protected fun renderBlock(node: TableBlock) {
    val last = currentTable!!
    currentTable = Table()

    visitChildren(node)

    val style = context.element.getStyle()
    context.row()
    context.lineOff += style.linesPadding
    context.draw(DrawTable.get(context.element, Table { t ->
      t.image().color(style.lineColor).width(1.5f).growY()
      t.add(currentTable).fill()
      t.image().color(style.lineColor).width(1.5f).growY()
    }, context.rendOff, -context.lineOff))

    context.updateHeight(currentTable!!.prefHeight)
    context.row()
    context.lineOff += style.linesPadding

    currentTable = last
  }

  protected fun renderHead(node: TableHead) {
    visitChildren(node)
  }

  protected fun renderBody(node: TableBody) {
    visitChildren(node)
  }

  protected fun renderRow(node: TableRow) {
    visitChildren(node)
    currentTable!!.row()
  }

  protected fun renderCell(node: TableCell) {
    val style = context.element.getStyle()
    currentTable!!.table(
      if (currentTable!!.rows%2 == 0) style.tableBack1 else style.tableBack2
    ) { t ->
      t!!.image().color(style.lineColor).width(1.5f).growY()
      t.table { ce ->
        ce!!.add(
          Markdown(
            context.element,
            node.firstChild
          )
        )
      }.grow().pad(style.linesPadding*2).get()
        .align(
          when (node.alignment) {
            TableCell.Alignment.LEFT -> Align.left
            TableCell.Alignment.RIGHT -> Align.right
            else -> Align.center
          }
        )
      t.image().color(style.lineColor).width(1.5f).growY()
    }.grow()
  }
}