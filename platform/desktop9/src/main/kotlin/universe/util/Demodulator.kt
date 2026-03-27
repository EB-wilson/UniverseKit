package universe.util

import sun.misc.Unsafe
import java.lang.reflect.Method
import java.util.*

/**反模块化工具，用于强制对需要的模块开放模块的软件包。在使用前请调用[setup]函数初始化本工具。
 *
 * 此类行为可能完全打破模块化的访问保护，本身是不安全的，若非是必要情况，请尽量避免使用该类。
 *
 * 同时本类型的副作用会破坏获得[java.lang.reflect.Field]的限制，如[jdk.internal.reflect.Reflection]等本不可反射字段的类型，
 * 在本工具初始化后将可反射所有字段。
 *
 * **此类仅在JDK9之后可用，不可在更早的版本中引用此类的方法。**
 *
 * @author EBwilson
 */
object Demodulator {
  private const val FILTER_OFFSET = 112L

  private lateinit var unsafe: Unsafe

  private var exportNative: Method? = null

  private var initialized = false

  private var opensOffset: Long = -1
  private var exportOffset: Long = -1

  fun setup() {
    if (initialized) return

    val cstr = Unsafe::class.java.getDeclaredConstructor()
    cstr.setAccessible(true)
    unsafe = cstr.newInstance()

    clearFieldFilter()

    val moduleClazz = Module::class.java
    val opensField = moduleClazz.getDeclaredField("openPackages")
    val exportField = moduleClazz.getDeclaredField("exportedPackages")

    opensOffset = unsafe.objectFieldOffset(opensField)
    exportOffset = unsafe.objectFieldOffset(exportField)

    makeModuleOpen(
      moduleClazz.module,
      "java.lang",
      Demodulator::class.java.module
    )

    exportNative = moduleClazz.getDeclaredMethod(
      "addExports0",
      moduleClazz, String::class.java, moduleClazz
    )
    exportNative!!.setAccessible(true)
    exportNative!!.invoke(
      null,
      moduleClazz.module, "java.lang", Demodulator::class.java.module
    )

    initialized = true
  }

  fun makeModuleOpen(from: Module, clazz: Class<*>, to: Module) {
    if (clazz.isPrimitive) return
    else if (clazz.isArray) makeModuleOpen(from, clazz.componentType, to)
    else makeModuleOpen(from, clazz.getPackage(), to)
  }

  fun makeModuleOpen(from: Module, pac: Package, to: Module) {
    if (isModuleOpen(from, pac, to)) return

    if (!initialized)
      throw IllegalStateException("Demodulator is not initialized yet, must invoke setup() method.")

    makeModuleOpen(from, pac.name, to)
  }

  fun isModuleOpen(from: Module, pac: Package, to: Module): Boolean {
    Objects.requireNonNull(from)
    Objects.requireNonNull(to)

    return from.isOpen(pac.name, to)
  }

  @Suppress("UNCHECKED_CAST")
  private fun makeModuleOpen(from: Module, pac: String, to: Module) {
    exportNative?.invoke(null, from, pac, to)

    var opensMap = unsafe.getObjectVolatile(from, opensOffset) as? MutableMap<String, MutableSet<Module>>
    if (opensMap == null) {
      opensMap = mutableMapOf()
      unsafe.putObjectVolatile(from, opensOffset, opensMap)
    }

    var exportsMap = unsafe.getObjectVolatile(from, exportOffset) as? MutableMap<String, MutableSet<Module>>
    if (exportsMap == null) {
      exportsMap = mutableMapOf()
      unsafe.putObjectVolatile(from, exportOffset, exportsMap)
    }

    val opens = opensMap.computeIfAbsent(pac) { e -> mutableSetOf() }
    val exports = exportsMap.computeIfAbsent(pac) { e -> mutableSetOf() }

    try {
      opens.add(to)
    } catch (e: UnsupportedOperationException) {
      val lis = opens.toMutableSet()
      lis.add(to)
      opensMap[pac] = lis
    }

    try {
      exports.add(to)
    } catch (e: UnsupportedOperationException) {
      val lis = exports.toMutableSet()
      lis.add(to)
      exportsMap[pac] = lis
    }
  }

  private fun clearFieldFilter() {
    val clazz = Class.forName("jdk.internal.reflect.Reflection")
    val map = unsafe.getObject(clazz, FILTER_OFFSET) as MutableMap<*, *>
    map.clear()
  }
}
