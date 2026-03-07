@file:Suppress("UNCHECKED_CAST")

package universe.util.reflect.accessor

import universe.UniverseActual
import java.lang.reflect.Method

abstract class MethodInvokerBase<R>(
  method: Method
){
  init {
    UniverseActual.reflection.makeAccessible(method)
  }

  internal val invoker = UniverseActual.reflection.obtainMethodInvoker<R>(method)
}

// Method invoker
class MethodInvoker0<O, R>(method: Method)
  : MethodInvokerBase<R>(method), (O) -> R {
  override fun invoke(self: O)
      = invoker.invoke(self)
}

class MethodInvoker1<O, P1, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1) -> R {
  override fun invoke(self: O, p1: P1)
      = invoker.invoke(self, p1)
}

class MethodInvoker2<O, P1, P2, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2) -> R {
  override fun invoke(self: O, p1: P1, p2: P2)
      = invoker.invoke(self, p1, p2)
}

class MethodInvoker3<O, P1, P2, P3, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3)
      = invoker.invoke(self, p1, p2, p3)
}

class MethodInvoker4<O, P1, P2, P3, P4, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4)
      = invoker.invoke(self, p1, p2, p3, p4)
}

class MethodInvoker5<O, P1, P2, P3, P4, P5, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
      = invoker.invoke(self, p1, p2, p3, p4, p5)
}

class MethodInvoker6<O, P1, P2, P3, P4, P5, P6, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6)
}

class MethodInvoker7<O, P1, P2, P3, P4, P5, P6, P7, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7)
}

class MethodInvoker8<O, P1, P2, P3, P4, P5, P6, P7, P8, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8)
}

class MethodInvoker9<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9)
}

class MethodInvoker10<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
}

class MethodInvoker11<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
}

class MethodInvoker12<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
}

class MethodInvoker13<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
}

class MethodInvoker14<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
}

class MethodInvoker15<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
}

class MethodInvoker16<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
}

class MethodInvoker17<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
}

class MethodInvoker18<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
}

class MethodInvoker19<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
}

class MethodInvoker20<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R>(method: Method)
  : MethodInvokerBase<R>(method), (O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) -> R {
  override fun invoke(self: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20)
      = invoker.invoke(self, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
}