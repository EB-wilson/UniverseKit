package universe.actuals.reflect

import universe.expects.reflect.FieldAccessor
import java.lang.invoke.MethodHandles
import java.lang.reflect.Field

class DesktopFieldAccessor(
  private val field: Field,
): FieldAccessor {
  override fun get(obj: Any): Any? = field.get(obj)
  override fun getBoolean(obj: Any) = field.getBoolean(obj)
  override fun getByte(obj: Any) = field.getByte(obj)
  override fun getChar(obj: Any) = field.getChar(obj)
  override fun getShort(obj: Any) = field.getShort(obj)
  override fun getInt(obj: Any) = field.getInt(obj)
  override fun getLong(obj: Any) = field.getLong(obj)
  override fun getFloat(obj: Any) = field.getFloat(obj)
  override fun getDouble(obj: Any) = field.getDouble(obj)

  override fun getStatic(): Any? = field.get(null)
  override fun getStaticBoolean() = field.getBoolean(null)
  override fun getStaticByte() = field.getByte(null)
  override fun getStaticChar() = field.getChar(null)
  override fun getStaticShort() = field.getShort(null)
  override fun getStaticInt() = field.getInt(null)
  override fun getStaticLong() = field.getLong(null)
  override fun getStaticFloat() = field.getFloat(null)
  override fun getStaticDouble() = field.getDouble(null)

  override fun set(obj: Any, value: Any?){ field.set(obj, value) }
  override fun setBoolean(obj: Any, z: Boolean){ field.setBoolean(obj, z) }
  override fun setByte(obj: Any, b: Byte) { field.setByte(obj, b) }
  override fun setChar(obj: Any, c: Char) { field.setChar(obj, c) }
  override fun setShort(obj: Any, s: Short) { field.setShort(obj, s) }
  override fun setInt(obj: Any, i: Int) { field.setInt(obj, i) }
  override fun setLong(obj: Any, l: Long) { field.setLong(obj, l) }
  override fun setFloat(obj: Any, f: Float) { field.setFloat(obj, f) }
  override fun setDouble(obj: Any, d: Double) { field.setDouble(obj, d) }

  override fun setStatic(value: Any?) { field.set(null, value) }
  override fun setStaticBoolean(z: Boolean) { field.setBoolean(null, z) }
  override fun setStaticByte(b: Byte) { field.setByte(null, b) }
  override fun setStaticChar(c: Char) { field.setChar(null, c) }
  override fun setStaticShort(s: Short) { field.setShort(null, s) }
  override fun setStaticInt(i: Int) { field.setInt(null, i) }
  override fun setStaticLong(l: Long) { field.setLong(null, l) }
  override fun setStaticFloat(f: Float) { field.setFloat(null, f) }
  override fun setStaticDouble(d: Double) { field.setDouble(null, d) }
}