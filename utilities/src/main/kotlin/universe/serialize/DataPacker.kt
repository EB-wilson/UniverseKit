package universe.serialize

import arc.util.io.Reads
import arc.util.io.Writes
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

/**Data Packaging Tool Class, used to package a [Packable] as binary data,
 * or to unpack [Packable] objects from binary data.
 *
 * @author EBwilson
 * @since 1.5*/
object DataPacker {
  /**Wrap this [Packable] object as binary data.
   *
   * @return The binary data representing the [Packable] object*/
  @JvmStatic
  fun Packable.packArray(): ByteArray {
    val arrayStream = ByteArrayOutputStream()
    this.pack(arrayStream)
    return arrayStream.toByteArray()
  }

  /**Wrap this [Packable] object as binary data and written to the given output stream.
   *
   * @param out The target output stream to be written*/
  @JvmStatic
  fun Packable.pack(out: OutputStream) {
    val name = typeName
    val write = Writes(DataOutputStream(out))

    write.str(name)
    write(write)
  }

  /**@see unpack*/
  @JvmStatic
  fun <T: Packable> ByteArray.unpackArray(
    provider: () -> T
  ): T {
    return inputStream().unpack(provider)
  }

  /**@see unpack*/
  @JvmStatic
  fun <T: Packable> ByteArray.unpackArray(
    classLoader: ClassLoader = DataPacker::class.java.classLoader
  ): T {
    return inputStream().unpack(classLoader)
  }

  /**Obtain the constructor object from the given function, unpackage the object data in this input stream,
   * and write it to the object.
   *
   * @param provider Constructor of the Packable object
   * @throws IllegalArgumentException If the type of object provided does not match the type encoded in the data in the input stream*/
  @JvmStatic
  fun <T: Packable> InputStream.unpack(
    provider: () -> T
  ): T {
    val read = Reads(DataInputStream(this))
    val className = read.str()
    val inst = provider()

    if (inst.typeName != className)
      throw IllegalArgumentException("Invalid type for packable, expected ${inst.typeName} but got $className")

    inst.read(read)

    return inst
  }

  /**From this input stream, unpack a [Packable] object encoded with the data inside,
   * providing a [ClassLoader] to parse type names to obtain the type of the unpackaged instance.
   *
   * **Note**: The target type of unpacking for this method must include a no-arguments constructor.
   *
   * @param classLoader The class loader used to search for the type name when parsing the class name
   * @throws IllegalArgumentException If the parsed type is not an implementation of Packable,
   *                                  or the parsed class name does not match the actual `typeName` of class.
   * @throws NoSuchMethodException If the parsed type does not have a no-arguments constructor.
   */
  @Suppress("UNCHECKED_CAST")
  @JvmStatic
  fun <T: Packable> InputStream.unpack(
    classLoader: ClassLoader = DataPacker::class.java.classLoader
  ): T {
    val read = Reads(DataInputStream(this))
    val className = read.str()

    val clazz = classLoader.loadClass(className)
    if (!Packable::class.java.isAssignableFrom(clazz))
      throw IllegalArgumentException("Type $className is not a Packable class.")

    val obj =
      try {
        clazz
          .getDeclaredConstructor()
          .also { it.isAccessible = true }
          .newInstance() as Packable
      } catch (e: NoSuchMethodException) {
        throw NoSuchMethodException("Packable type $className must have a no-arg constructor.")
      }

    if (className != obj.typeName)
      throw IllegalArgumentException("Invalid type for packable, expected $className but got ${read.str()}")
    obj.read(read)

    return obj as T
  }
}