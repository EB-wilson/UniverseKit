package universe.util.reflect.accessor

import universe.UniverseActual
import java.lang.reflect.Method

abstract class StaticInvokerBase<R>(
  method: Method
){
  init {
    UniverseActual.reflection.makeAccessible(method)
  }

  internal val method = UniverseActual.reflection.obtainStaticInvoker<R>(method)
}

// static
class StaticInvoker0<R>(method: Method)
  : StaticInvokerBase<R>(method), () -> R {
  override fun invoke() = method.invoke()
}

class StaticInvoker1<P1, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1) -> R {
  override fun invoke(p1: P1)
      = method.invoke(p1)
}

class StaticInvoker2<P1, P2, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2) -> R {
  override fun invoke(p1: P1, p2: P2)
      = method.invoke(p1, p2)
}

class StaticInvoker3<P1, P2, P3, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3)
      = method.invoke(p1, p2, p3)
}

class StaticInvoker4<P1, P2, P3, P4, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4)
      = method.invoke(p1, p2, p3, p4)
}

class StaticInvoker5<P1, P2, P3, P4, P5, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
      = method.invoke(p1, p2, p3, p4, p5)
}

class StaticInvoker6<P1, P2, P3, P4, P5, P6, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6)
      = method.invoke(p1, p2, p3, p4, p5, p6)
}

class StaticInvoker7<P1, P2, P3, P4, P5, P6, P7, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7)
}

class StaticInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8)
}

class StaticInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9)
}

class StaticInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
}

class StaticInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
}

class StaticInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
}

class StaticInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
}

class StaticInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
}

class StaticInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
}

class StaticInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
}

class StaticInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
}

class StaticInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
}

class StaticInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
}

class StaticInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R>(method: Method)
  : StaticInvokerBase<R>(method), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) -> R {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20)
      = method.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
}