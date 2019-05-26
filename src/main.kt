import kotlin.system.measureTimeMillis

fun main() {
    println(sum())
    println(sum(1, 2, 3, 4, 5))
    println(callName("田中"))
    println(listSort(listOf("c", "b", "a", "d")))
    stringJoinSpeed()
}

/**
 * 足し算
 */
fun sum(a: Int, b: Int) = a + b

/**
 * 足し算（可変長引数）
 */
fun sum(vararg numbers: Int): Int {
    var result = 0
    numbers.forEach { number -> result += number }
//    for (number in numbers) {
//        result += number
//    }
    return result
}

/**
 * テンプレート文字列
 */
fun callName(name: String) = "こんにちは、${name}さん"

/**
 * List のソート
 */
fun listSort(list: List<String>) = list.sorted()

/**
 * 文字連結の速度をテスト（StringBuilder がプラス演算子の3500倍速い）
 */
fun stringJoinSpeed() {
    val joinCount = 10000
    println("文字連結を ${joinCount} 回行う速度を計測する")
    var a = "a"
    val timeThatByPlus = measureTimeMillis {
        repeat(joinCount) {
            a += "a"
        }
    }
    println("プラス演算子による連結 : ${timeThatByPlus}ms")

    val b = StringBuilder("b")
    val timeThatByBuilder = measureTimeMillis {
        repeat(joinCount) {
            b.append("b")
        }
    }
    println("StringBuilderによる連結 : ${timeThatByBuilder}ms")
}