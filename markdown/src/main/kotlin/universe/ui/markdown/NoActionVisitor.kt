package universe.ui.markdown

import org.commonmark.node.BlockQuote
import org.commonmark.node.BulletList
import org.commonmark.node.Code
import org.commonmark.node.CustomBlock
import org.commonmark.node.CustomNode
import org.commonmark.node.Document
import org.commonmark.node.Emphasis
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.HardLineBreak
import org.commonmark.node.Heading
import org.commonmark.node.HtmlBlock
import org.commonmark.node.HtmlInline
import org.commonmark.node.Image
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.Link
import org.commonmark.node.LinkReferenceDefinition
import org.commonmark.node.ListItem
import org.commonmark.node.OrderedList
import org.commonmark.node.Paragraph
import org.commonmark.node.SoftLineBreak
import org.commonmark.node.StrongEmphasis
import org.commonmark.node.Text
import org.commonmark.node.ThematicBreak
import org.commonmark.node.Visitor

abstract class NoActionVisitor: Visitor {
  override fun visit(blockQuote: BlockQuote) {}
  override fun visit(bulletList: BulletList) {}
  override fun visit(code: Code) {}
  override fun visit(document: Document) {}
  override fun visit(emphasis: Emphasis) {}
  override fun visit(fencedCodeBlock: FencedCodeBlock) {}
  override fun visit(hardLineBreak: HardLineBreak) {}
  override fun visit(heading: Heading) {}
  override fun visit(thematicBreak: ThematicBreak) {}
  override fun visit(htmlInline: HtmlInline) {}
  override fun visit(htmlBlock: HtmlBlock) {}
  override fun visit(image: Image) {}
  override fun visit(indentedCodeBlock: IndentedCodeBlock) {}
  override fun visit(link: Link) {}
  override fun visit(listItem: ListItem) {}
  override fun visit(orderedList: OrderedList) {}
  override fun visit(paragraph: Paragraph) {}
  override fun visit(softLineBreak: SoftLineBreak) {}
  override fun visit(strongEmphasis: StrongEmphasis) {}
  override fun visit(text: Text) {}
  override fun visit(linkReferenceDefinition: LinkReferenceDefinition) {}
  override fun visit(customBlock: CustomBlock) {}
  override fun visit(customNode: CustomNode) {}
}