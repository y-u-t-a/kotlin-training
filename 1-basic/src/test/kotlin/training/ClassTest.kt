package training

import kotlin.test.*

class ClassTest {
    @Test
    fun notEqualClass() {
        val u1 = User("User", 20)
        val u2 = User("User", 20)
        assertNotEquals(u1, u2)
        assertFalse { u1 == u2 }
    }

    @Test
    fun dataClass() {
        val u1 = DataClassUser("User", 20)
        val u2 = DataClassUser("User", 20)
        assertEquals(u1, u2)
        assertTrue { u1 == u2 }
    }
}