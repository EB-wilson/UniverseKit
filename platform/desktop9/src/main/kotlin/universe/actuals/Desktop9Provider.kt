package universe.actuals

import universe.expects.PlatformProvider
import universe.expects.ReflectionHandle
import universe.util.Demodulator

class Desktop9Provider: PlatformProvider {
  init {
    Demodulator.setup()
  }

  override fun getReflectionHandle(): ReflectionHandle = ReflectionDesktop9()
}