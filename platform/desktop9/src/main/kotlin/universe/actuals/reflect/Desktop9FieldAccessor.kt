package universe.actuals.reflect

import universe.expects.reflect.FieldAccessor
import java.lang.invoke.MethodHandles
import java.lang.reflect.Field

class Desktop9FieldAccessor(
  field: Field,
): FieldAccessor {
  companion object{
    private val lookup = MethodHandles.lookup()
  }

  private val getter = lookup.unreflectGetter(field)!!
  private val setter = lookup.unreflectSetter(field)!!

  override fun get(obj: Any): Any? = getter.invoke(obj)
  override fun getBoolean(obj: Any) = getter.invoke(obj) as Boolean
  override fun getByte(obj: Any) = getter.invoke(obj) as Byte
  override fun getChar(obj: Any) = getter.invoke(obj) as Char
  override fun getShort(obj: Any) = getter.invoke(obj) as Short
  override fun getInt(obj: Any) = getter.invoke(obj) as Int
  override fun getLong(obj: Any) = getter.invoke(obj) as Long
  override fun getFloat(obj: Any) = getter.invoke(obj) as Float
  override fun getDouble(obj: Any) = getter.invoke(obj) as Double

  override fun getStatic(): Any? = getter.invoke()
  override fun getStaticBoolean() = getter.invoke() as Boolean
  override fun getStaticByte() = getter.invoke() as Byte
  override fun getStaticChar() = getter.invoke() as Char
  override fun getStaticShort() = getter.invoke() as Short
  override fun getStaticInt() = getter.invoke() as Int
  override fun getStaticLong() = getter.invoke() as Long
  override fun getStaticFloat() = getter.invoke() as Float
  override fun getStaticDouble() = getter.invoke() as Double

  override fun set(obj: Any, value: Any?){ setter.invoke(obj, value) }
  override fun setBoolean(obj: Any, z: Boolean){ setter.invoke(obj, z) }
  override fun setByte(obj: Any, b: Byte) { setter.invoke(obj, b) }
  override fun setChar(obj: Any, c: Char) { setter.invoke(obj, c) }
  override fun setShort(obj: Any, s: Short) { setter.invoke(obj, s) }
  override fun setInt(obj: Any, i: Int) { setter.invoke(obj, i) }
  override fun setLong(obj: Any, l: Long) { setter.invoke(obj, l) }
  override fun setFloat(obj: Any, f: Float) { setter.invoke(obj, f) }
  override fun setDouble(obj: Any, d: Double) { setter.invoke(obj, d) }

  override fun setStatic(value: Any?) { setter.invoke(value) }
  override fun setStaticBoolean(z: Boolean) { setter.invoke(z) }
  override fun setStaticByte(b: Byte) { setter.invoke(b) }
  override fun setStaticChar(c: Char) { setter.invoke(c) }
  override fun setStaticShort(s: Short) { setter.invoke(s) }
  override fun setStaticInt(i: Int) { setter.invoke(i) }
  override fun setStaticLong(l: Long) { setter.invoke(l) }
  override fun setStaticFloat(f: Float) { setter.invoke(f) }
  override fun setStaticDouble(d: Double) { setter.invoke(d) }
}