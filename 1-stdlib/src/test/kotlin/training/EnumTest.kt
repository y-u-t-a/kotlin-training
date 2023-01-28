package training

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTest {
    @Test
    fun testCompareToString() {
        assertEquals("MONDAY", DayOfWeek.MONDAY.name)
    }

    @Test
    fun testConstructor() {
        // 列挙子の名前から enum を生成する
        val sunDay = DayOfWeek.valueOf("SUNDAY")
        assertEquals(DayOfWeek.SUNDAY, sunDay)
    }

    @Test
    fun testNotMatchValue() {
        assertThrows<IllegalArgumentException> {
            // valueOf の引数の値が enum に存在しない場合、IllegalArgumentException がスローされる
            DayOfWeek.valueOf("DAYDAY")
        }
    }

    @Test
    fun testIgnoreCase() {
        assertThrows<IllegalArgumentException> {
            // Enum の値は大文字・小文字が区別される
            DayOfWeek.valueOf("sunday")
        }
    }

    @Test
    fun testMultiValue() {
        assertEquals(PrefCode.AOMORI.code, "02")
        assertEquals(PrefCode.AOMORI.name, "AOMORI")
    }

    @Test
    fun testCustomConstructor() {
        val hokkaido = PrefCode.fromCode("01")
        assertEquals(PrefCode.HOKKAIDO, hokkaido)
    }

    @Test
    fun testInvalidCode() {
        assertThrows<IllegalArgumentException> {
            PrefCode.fromCode("99")
        }
    }
}