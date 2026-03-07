package universe.expects.reflect

interface FieldAccessor {
  //Getters
  fun get(obj: Any): Any?
  fun getBoolean(obj: Any): Boolean
  fun getByte(obj: Any): Byte
  fun getChar(obj: Any): Char
  fun getShort(obj: Any): Short
  fun getInt(obj: Any): Int
  fun getLong(obj: Any): Long
  fun getFloat(obj: Any): Float
  fun getDouble(obj: Any): Double

  fun getStatic(): Any?
  fun getStaticBoolean(): Boolean
  fun getStaticByte(): Byte
  fun getStaticChar(): Char
  fun getStaticShort(): Short
  fun getStaticInt(): Int
  fun getStaticLong(): Long
  fun getStaticFloat(): Float
  fun getStaticDouble(): Double

  //Setters
  fun set(obj: Any, value: Any?)
  fun setBoolean(obj: Any, z: Boolean)
  fun setByte(obj: Any, b: Byte)
  fun setChar(obj: Any, c: Char)
  fun setShort(obj: Any, s: Short)
  fun setInt(obj: Any, i: Int)
  fun setLong(obj: Any, l: Long)
  fun setFloat(obj: Any, f: Float)
  fun setDouble(obj: Any, d: Double)

  fun setStatic(value: Any?)
  fun setStaticBoolean(z: Boolean)
  fun setStaticByte(b: Byte)
  fun setStaticChar(c: Char)
  fun setStaticShort(s: Short)
  fun setStaticInt(i: Int)
  fun setStaticLong(l: Long)
  fun setStaticFloat(f: Float)
  fun setStaticDouble(d: Double)
}