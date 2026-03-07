package universe.ui.markdown.extensions.curtain

import org.commonmark.node.Visitor

interface CurtainVisitor: Visitor {
  fun visit(visitor: Curtain)
}