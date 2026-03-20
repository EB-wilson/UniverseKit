package universe.ui.markdown

import arc.graphics.Color
import mindustry.ui.Fonts
import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableRow
import org.commonmark.ext.ins.Ins
import org.commonmark.node.*
import universe.ui.markdown.elemdraw.DrawStr.Companion.addTextWrap
import universe.ui.markdown.extensions.curtain.Curtain
import universe.ui.markdown.extensions.curtain.CurtainExtension
import universe.ui.markdown.extensions.curtain.CurtainProvider
import universe.ui.markdown.extensions.ins.InsExtension
import universe.ui.markdown.extensions.ins.InsProvider
import universe.ui.markdown.extensions.strikethrough.StrikethroughExtension
import universe.ui.markdown.extensions.strikethrough.StrikethroughProvider
import universe.ui.markdown.extensions.table.TableProvider
import universe.ui.markdown.extensions.table.TablesExtension
import javax.swing.table.TableColumn

class BaseProvider: MarkdownProvider, CurtainProvider, InsProvider, StrikethroughProvider, TableProvider {
  override fun extensions() = listOf(
    TablesExtension.create(),
    InsExtension.create(),
    StrikethroughExtension.create(),
    CurtainExtension.create()
  )

  override fun RendererContext.add(node: Document) {
    //always create a root scope, set the max width.
    withScope(
      maxWidth = if (mdWidth <= 0) -1f else mdWidth
    ) {
      renderChildren(node)
    }
  }

  override fun RendererContext.add(node: Heading) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Paragraph) {
    renderChildren(node)
    row(mdStyle.paragraphPadding)
  }

  override fun RendererContext.add(node: BlockQuote) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: BulletList) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: FencedCodeBlock) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: HtmlBlock) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: ThematicBreak) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: IndentedCodeBlock) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Link) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: ListItem) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: OrderedList) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Image) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Emphasis) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: StrongEmphasis) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Text) {
    addTextWrap(
      str = node.literal,
      font = getScope().font,
      color = Color.white,
      scl = 1f,
    )
  }

  override fun RendererContext.add(node: Code) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: HtmlInline) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: SoftLineBreak) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: HardLineBreak) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: LinkReferenceDefinition) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Curtain) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Ins) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: Strikethrough) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: TableBlock) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: TableHead) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: TableBody) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: TableRow) {
    TODO("Not yet implemented")
  }

  override fun RendererContext.add(node: TableColumn) {
    TODO("Not yet implemented")
  }
}