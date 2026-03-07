package universe.ui.markdown

import org.commonmark.Extension
import org.commonmark.node.*

interface MarkdownProvider {
  fun extensions(): List<Extension>

  fun RendererContext.add(node: Document)
  fun RendererContext.add(node: Heading)
  fun RendererContext.add(node: Paragraph)
  fun RendererContext.add(node: BlockQuote)
  fun RendererContext.add(node: BulletList)
  fun RendererContext.add(node: FencedCodeBlock)
  fun RendererContext.add(node: HtmlBlock)
  fun RendererContext.add(node: ThematicBreak)
  fun RendererContext.add(node: IndentedCodeBlock)
  fun RendererContext.add(node: Link)
  fun RendererContext.add(node: ListItem)
  fun RendererContext.add(node: OrderedList)
  fun RendererContext.add(node: Image)
  fun RendererContext.add(node: Emphasis)
  fun RendererContext.add(node: StrongEmphasis)
  fun RendererContext.add(node: Text)
  fun RendererContext.add(node: Code)
  fun RendererContext.add(node: HtmlInline)
  fun RendererContext.add(node: SoftLineBreak)
  fun RendererContext.add(node: HardLineBreak)
  fun RendererContext.add(node: LinkReferenceDefinition)

  fun RendererContext.add(node: CustomNode){
    throw UnsupportedOperationException("node type ${node.javaClass} not supported")
  }
  fun RendererContext.add(node: CustomBlock){
    throw UnsupportedOperationException("block type ${node.javaClass} not supported")
  }
}