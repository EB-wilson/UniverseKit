@file:Suppress("HasPlatformType")

package universe.util.reflect.accessor

import universe.UniverseActual
import java.lang.reflect.Constructor

abstract class ConstructorInvokerBase<T: Any>(
  constructor: Constructor<T>
){
  init {
    UniverseActual.reflection.makeAccessible(constructor)
  }

  internal val constructor = UniverseActual.reflection.obtainConstructorInvoker(constructor)
}

// Constructor invoker
class ConstructorInvoker0<O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), () -> O {
  override fun invoke()
      = constructor.newInstance()
}

class ConstructorInvoker1<P1, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1) -> O {
  override fun invoke(p1: P1)
      = constructor.newInstance(p1)
}

class ConstructorInvoker2<P1, P2, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2) -> O {
  override fun invoke(p1: P1, p2: P2)
      = constructor.newInstance(p1, p2)
}

class ConstructorInvoker3<P1, P2, P3, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3)
      = constructor.newInstance(p1, p2, p3)
}

class ConstructorInvoker4<P1, P2, P3, P4, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4)
      = constructor.newInstance(p1, p2, p3, p4)
}

class ConstructorInvoker5<P1, P2, P3, P4, P5, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
      = constructor.newInstance(p1, p2, p3, p4, p5)
}

class ConstructorInvoker6<P1, P2, P3, P4, P5, P6, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6)
}

class ConstructorInvoker7<P1, P2, P3, P4, P5, P6, P7, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7)
}

class ConstructorInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8)
}

class ConstructorInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9)
}

class ConstructorInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
}

class ConstructorInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
}

class ConstructorInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
}

class ConstructorInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
}

class ConstructorInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
}

class ConstructorInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
}

class ConstructorInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
}

class ConstructorInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
}

class ConstructorInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
}

class ConstructorInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
}

class ConstructorInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, O: Any>(constructor: Constructor<O>)
  : ConstructorInvokerBase<O>(constructor), (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) -> O {
  override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20)
      = constructor.newInstance(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
}