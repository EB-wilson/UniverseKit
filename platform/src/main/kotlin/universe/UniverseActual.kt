package universe

import universe.actuals.Android29Provider
import universe.actuals.AndroidProvider
import universe.actuals.Desktop9Provider
import universe.actuals.DesktopProvider
import universe.expects.PlatformProvider

object UniverseActual {
  private var platformProvider = getPlatform()

  var reflection = platformProvider.getReflectionHandle()
    private set

  fun customPlatformProvider(platform: PlatformProvider) {
    platformProvider = platform

    reflection = platform.getReflectionHandle()
  }

  private fun getPlatform(): PlatformProvider {
    try {
      val ver = Class.forName($$"android.os.Build$VERSION")
      val sdkField = ver.getField("SDK_INT")
      sdkField.isAccessible = true

      return if (sdkField.getInt(null) >= 29) Android29Provider()
      else AndroidProvider()
    } catch (_:ClassNotFoundException){}

    try {
      Class.forName("java.lang.Module")
      return Desktop9Provider()
    } catch (_:ClassNotFoundException){}

    return DesktopProvider()
  }
}