import universe.util.reflect.Reflection.accessField
import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaMethod

enum class Te{
  A, B, C
}

fun main(){
  val n: KFunction<EnumEntries<Te>> = ::enumEntries

  n.javaMethod!!.declaringClass.declaredMethods.forEach { println(it) }
}