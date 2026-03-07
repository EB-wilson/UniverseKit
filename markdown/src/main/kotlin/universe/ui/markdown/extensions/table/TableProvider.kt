package universe.ui.markdown.extensions.table

import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableRow
import universe.ui.markdown.MarkdownProvider
import universe.ui.markdown.RendererContext
import javax.swing.table.TableColumn

interface TableProvider: MarkdownProvider {
  fun RendererContext.add(node: TableBlock)
  fun RendererContext.add(node: TableHead)
  fun RendererContext.add(node: TableBody)
  fun RendererContext.add(node: TableRow)
  fun RendererContext.add(node: TableColumn)
}