package universe.ui.markdown.extensions.curtain

import org.commonmark.node.CustomNode
import org.commonmark.node.Visitor

class Curtain : CustomNode(){
  override fun accept(visitor: Visitor) {
    if (visitor is CurtainVisitor) visitor.visit(this)
    else super.accept(visitor)
  }
}
