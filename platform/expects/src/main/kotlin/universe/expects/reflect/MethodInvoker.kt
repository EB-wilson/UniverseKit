package universe.expects.reflect

interface MethodInvoker<R> {
  fun <O> invoke(obj: O): R
  fun <O, P1> invoke(obj: O, p1: P1): R
  fun <O, P1, P2> invoke(obj: O, p1: P1, p2: P2): R
  fun <O, P1, P2, P3> invoke(obj: O, p1: P1, p2: P2, p3: P3): R
  fun <O, P1, P2, P3, P4> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4): R
  fun <O, P1, P2, P3, P4, P5> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): R
  fun <O, P1, P2, P3, P4, P5, P6> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): R
  fun <O, P1, P2, P3, P4, P5, P6, P7> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19): R
  fun <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20>
      invoke(obj: O, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20): R
}