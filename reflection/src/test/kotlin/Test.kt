import universe.util.reflect.Enums.accessEnum0
import universe.util.reflect.Reflection.accessField

class Test{
  private val str: String = "asdaf"
}

val Test.a: String? by accessField("str")

fun main(){
}