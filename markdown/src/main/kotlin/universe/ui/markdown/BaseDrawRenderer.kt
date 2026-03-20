package universe.ui.markdown

import org.commonmark.node.*

open class BaseDrawRenderer(
  context: RendererContext,
  provider: BaseProvider
): LayoutNodeRenderer<BaseProvider>(context, provider) {
  companion object {
    val TypeSet = setOf<Class<out Node>>(
      Document::class.java,
      Heading::class.java,
      Paragraph::class.java,
      BlockQuote::class.java,
      BulletList::class.java,
      FencedCodeBlock::class.java,
      HtmlBlock::class.java,
      ThematicBreak::class.java,
      IndentedCodeBlock::class.java,
      Link::class.java,
      ListItem::class.java,
      OrderedList::class.java,
      Image::class.java,
      Emphasis::class.java,
      StrongEmphasis::class.java,
      Text::class.java,
      Code::class.java,
      HtmlInline::class.java,
      SoftLineBreak::class.java,
      HardLineBreak::class.java
    )
  }

  private inner class BaseVisitor: Visitor{
    override fun visit(blockQuote: BlockQuote) { provider.apply { context.add(blockQuote) } }
    override fun visit(bulletList: BulletList) { provider.apply { context.add(bulletList) } }
    override fun visit(code: Code) { provider.apply { context.add(code) } }
    override fun visit(document: Document) { provider.apply { context.add(document) } }
    override fun visit(emphasis: Emphasis) { provider.apply { context.add(emphasis) } }
    override fun visit(fencedCodeBlock: FencedCodeBlock) { provider.apply { context.add(fencedCodeBlock) } }
    override fun visit(hardLineBreak: HardLineBreak) { provider.apply { context.add(hardLineBreak) } }
    override fun visit(heading: Heading) { provider.apply { context.add(heading) } }
    override fun visit(thematicBreak: ThematicBreak) { provider.apply { context.add(thematicBreak) } }
    override fun visit(htmlInline: HtmlInline) { provider.apply { context.add(htmlInline) } }
    override fun visit(htmlBlock: HtmlBlock) { provider.apply { context.add(htmlBlock) } }
    override fun visit(image: Image) { provider.apply { context.add(image) } }
    override fun visit(indentedCodeBlock: IndentedCodeBlock) { provider.apply { context.add(indentedCodeBlock) } }
    override fun visit(link: Link) { provider.apply { context.add(link) } }
    override fun visit(listItem: ListItem) { provider.apply { context.add(listItem) } }
    override fun visit(orderedList: OrderedList) { provider.apply { context.add(orderedList) } }
    override fun visit(paragraph: Paragraph) { provider.apply { context.add(paragraph) } }
    override fun visit(softLineBreak: SoftLineBreak) { provider.apply { context.add(softLineBreak) } }
    override fun visit(strongEmphasis: StrongEmphasis) { provider.apply { context.add(strongEmphasis) } }
    override fun visit(text: Text) { provider.apply { context.add(text) } }
    override fun visit(linkReferenceDefinition: LinkReferenceDefinition) { provider.apply { context.add(linkReferenceDefinition) } }
    override fun visit(customBlock: CustomBlock) { provider.apply { context.add(customBlock) } }
    override fun visit(customNode: CustomNode) { provider.apply { context.add(customNode) } }
  }
  private val visitorInst = BaseVisitor()

  override fun getNodeTypes(): Set<Class<out Node>> {
    return TypeSet
  }

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}
