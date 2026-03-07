package universe.ui.markdown.extensions.curtain

import org.commonmark.node.Node
import org.commonmark.node.Nodes
import org.commonmark.node.SourceSpans
import org.commonmark.parser.delimiter.DelimiterProcessor
import org.commonmark.parser.delimiter.DelimiterRun

class CurtainDelimiterProcessor : DelimiterProcessor {
  override fun getOpeningCharacter(): Char {
    return delem
  }

  override fun getClosingCharacter(): Char {
    return delem
  }

  override fun getMinLength(): Int {
    return 1
  }

  override fun process(openingRun: DelimiterRun, closingRun: DelimiterRun): Int {
    if (openingRun.length() == 1 && closingRun.length() == 1) {
      val sourceSpans = SourceSpans()
      sourceSpans.addAllFrom(openingRun.getOpeners(1))
      val opener = openingRun.opener

      val res: Node = Curtain()
      for (node in Nodes.between(opener, closingRun.closer)) {
        res.appendChild(node)
        sourceSpans.addAll(node.sourceSpans)
      }
      sourceSpans.addAllFrom(closingRun.getClosers(1))
      res.sourceSpans = sourceSpans.sourceSpans
      opener.insertAfter(res)

      return 1
    }
    else {
      return 0
    }
  }

  companion object {
    private const val delem = '$'
  }
}
