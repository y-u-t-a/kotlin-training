package training

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * 拡張関数・プロパティをテストする
 *
 * [Docs](https://kotlinlang.org/docs/extensions.html)
 */
class ExtensionsTest {
    private fun <T> Collection<T>?.toCsv() : String {
        return this?.joinToString(",") ?: ""
    }
    private val <T> Collection<T>.lastIndex: Int
        get() = size - 1

    @Test
    fun extensionFunction() {
        val l = listOf("A", "B", "C")
        assertEquals("A,B,C", l.toCsv())
        val n: List<String>? = null
        assertEquals("", n.toCsv())
    }

    @Test
    fun extensionProperty() {
        val l = listOf("A", "B", "C")
        assertEquals(2, l.lastIndex)
    }
}