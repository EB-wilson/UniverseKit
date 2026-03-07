package universe.ui.markdown.extensions.table

import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableCell
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableRow
import org.commonmark.node.Visitor

interface TableVisitor: Visitor {
  fun visit(visitor: TableBlock)
  fun visit(visitor: TableHead)
  fun visit(visitor: TableBody)
  fun visit(visitor: TableRow)
  fun visit(visitor: TableCell)
}