package universe.actuals.reflect

import universe.expects.reflect.MethodInvoker
import java.lang.reflect.Method

@Suppress("UNCHECKED_CAST")
class Android29MethodInvoker<R>(
  private val method: Method
): MethodInvoker<R> {
  override fun <O> invoke(obj: O) =
    method.invoke(obj) as R
  override fun <O, P1> invoke(obj: O, p1: P1) =
    method.invoke(obj, p1) as R
  override fun <O, P1, P2> invoke(obj: O, p1: P1, p2: P2) =
    method.invoke(obj, p1, p2) as R
  override fun <O, P1, P2, P3> invoke(obj: O, p1: P1, p2: P2, p3: P3) =
    method.invoke(obj, p1, p2, p3) as R
  override fun <O, P1, P2, P3, P4> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4) =
    method.invoke(obj, p1, p2, p3, p4) as R
  override fun <O, P1, P2, P3, P4, P5> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) =
    method.invoke(obj, p1, p2, p3, p4, p5) as R
  override fun <O, P1, P2, P3, P4, P5, P6>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16) =
    method.invoke(obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17) =
    method.invoke(
      obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17
    ) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18) =
    method.invoke(
      obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18
    ) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19) =
    method.invoke(
      obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19
    ) as R
  override fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20> invoke(
    obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20) =
    method.invoke(
      obj, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19, p20
    ) as R
}