package universe.ui.markdown.extensions.strikethrough

import org.commonmark.ext.gfm.strikethrough.Strikethrough
import org.commonmark.node.Visitor

interface StrikethroughVisitor: Visitor {
  fun visit(visitor: Strikethrough)
}