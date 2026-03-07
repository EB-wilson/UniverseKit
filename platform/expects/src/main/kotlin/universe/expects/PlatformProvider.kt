package universe.expects

interface PlatformProvider {
  fun getReflectionHandle(): ReflectionHandle
}