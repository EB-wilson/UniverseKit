package universe.serialize

import arc.util.io.Reads
import arc.util.io.Writes

/**Types that can be binary wrapped, similar to [Serializable][java.io.Serializable] objects in Java,
 * but the details of serialization are provided by the implementation type of this interface.
 *
 * You can package a packageable object into binary data using the [DataPacker.pack] and [DataPacker.unpack] methods in [DataPacker],
 * or unpack an instance of the data from the binary data.
 *
 * @author EBwilson
 * @since 1.5*/
interface Packable {
  /**The class name identifier used to identify the unpackaged type is written into binary data during packaging.
   * When unpacking, this name is used to determine the type of the instance and to construct a new instance.
   *
   * By default, the internal implementation automatically retrieves the nearest non-anonymous type of the current instance,
   * usually not implement [write] and [read] in anonymous classes.*/
  val typeName: String get() = run {
    var writeType: Class<*> = this::class.java
    while (writeType.isAnonymousClass) {
      writeType = writeType.superclass
    }
    writeType.name
  }

  /**Writing object data to a binary stream must be symmetric to the read flow in [read].*/
  fun write(write: Writes)
  /**Reading object data from a binary stream must be symmetric to the write flow in [write].*/
  fun read(read: Reads)
}