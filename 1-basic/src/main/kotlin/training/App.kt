package training

/**
 * main という関数はエントリーポイントとなる。
 * [Docs](https://kotlinlang.org/docs/basic-syntax.html#program-entry-point)
 */
fun main() {
    println("Hello World!")

    val user = User(name = "User", age = 22)
    println("Name: ${user.name}, Age: ${user.age}")

    val userData = DataClassUser(name = "User", age = 22)
    println(userData)
}
