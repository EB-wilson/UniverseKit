package universe.actuals

import universe.expects.PlatformProvider
import universe.expects.ReflectionHandle

class AndroidProvider: PlatformProvider {
  override fun getReflectionHandle(): ReflectionHandle = ReflectionAndroid()
}