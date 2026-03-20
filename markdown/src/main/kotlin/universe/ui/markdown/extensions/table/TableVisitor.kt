package universe.ui.markdown.extensions.table

import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableCell
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableRow
import org.commonmark.node.Visitor

interface TableVisitor: Visitor {
  fun visit(block: TableBlock)
  fun visit(head: TableHead)
  fun visit(body: TableBody)
  fun visit(row: TableRow)
  fun visit(cell: TableCell)
}