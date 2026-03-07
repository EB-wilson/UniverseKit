package universe.actuals

import universe.expects.PlatformProvider
import universe.expects.ReflectionHandle

class DesktopProvider: PlatformProvider {
  override fun getReflectionHandle(): ReflectionHandle = ReflectionDesktop()
}