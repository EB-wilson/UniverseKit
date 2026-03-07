package universe.actuals

import universe.expects.PlatformProvider
import universe.expects.ReflectionHandle

class Android29Provider: PlatformProvider {
  override fun getReflectionHandle(): ReflectionHandle = ReflectionAndroid29()
}