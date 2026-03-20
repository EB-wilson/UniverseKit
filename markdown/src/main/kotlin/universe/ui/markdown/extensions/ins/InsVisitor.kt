package universe.ui.markdown.extensions.ins

import org.commonmark.ext.ins.Ins
import org.commonmark.node.Visitor

interface InsVisitor: Visitor {
  fun visit(ins: Ins)
}