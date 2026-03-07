package universe.expects.reflect

interface ConstructorInvoker<T> {
  fun newInstance(): T
  fun <P1> newInstance(p1: P1): T
  fun <P1, P2> newInstance(p1: P1, p2: P2): T
  fun <P1, P2, P3> newInstance(p1: P1, p2: P2, p3: P3): T
  fun <P1, P2, P3, P4> newInstance(p1: P1, p2: P2, p3: P3, p4: P4): T
  fun <P1, P2, P3, P4, P5> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): T
  fun <P1, P2, P3, P4, P5, P6> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): T
  fun <P1, P2, P3, P4, P5, P6, P7> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19): T
  fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20): T
}