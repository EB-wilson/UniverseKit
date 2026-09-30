package universe.animation

import arc.math.Interp

class AnimationCurve(
  vararg val curveKeys: CurveKey
): Interp {
  override fun apply(a: Float): Float {
    TODO("Not yet implemented")
  }

  data class CurveKey(
    val time: Float,
    val value: Float,

    val tangentIn: Float,
    val tangentOut: Float,

    val weightIn: Float,
    val weightOut: Float,
  ){
    constructor(
      time: Float,
      value: Float,
      tangent: Float,
    ): this(time, value, tangent, tangent, 1f, 1f)

    constructor(
      time: Float,
      value: Float,
      tangentIn: Float,
      tangentOut: Float,
    ): this(time, value, tangentIn, tangentOut, 1f, 1f)
  }
}