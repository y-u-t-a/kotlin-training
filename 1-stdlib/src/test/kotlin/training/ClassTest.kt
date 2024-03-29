package training

import kotlin.test.*

class ClassTest {
    /**
     * Class は、インスタンスを値として比較できない
     */
    @Test
    fun notEqualClass() {
        val u1 = User("User", 20)
        val u2 = User("User", 20)
        assertNotEquals(u1, u2)
        assertTrue { u1 != u2 }
    }

    /**
     * DataClass は、インスタンスを値として比較できる
     */
    @Test
    fun dataClass() {
        val u1 = DataClassUser("User", 20)
        val u2 = DataClassUser("User", 20)
        assertEquals(u1, u2)
        assertTrue { u1 == u2 }
    }

    @Test
    fun backingProperty() {
        val u = BackingPropertyUser("User")
        assertEquals(u.name, "USER")

        val e = BackingPropertyUser(null)
        assertEquals(e.name, "EMPTY")
    }
}