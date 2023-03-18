package training

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * kotlinx-serialization-json を使って JSON シリアライズをテスト
 *
 * [Docs](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-json/kotlinx.serialization.json/)
 */
class KotlinxJsonTest {
    private val jsonSerializer = Json { ignoreUnknownKeys = true }

    @Test
    fun deserializeJson() {
        val actual = jsonSerializer.decodeFromString<Product>(SAMPLE_JSON_1)
        assertEquals(sampleObject1, actual)
    }

    @Test
    fun deserializeJsonArray() {
        val jsonArray = "[$SAMPLE_JSON_1, $SAMPLE_JSON_2]"
        val actual = jsonSerializer.decodeFromString<List<Product>>(jsonArray)
        assertEquals(listOf(sampleObject1, sampleObject2), actual)
    }

    @Test
    fun serializeToJsonElement() {
        // JsonElement に変換
        val actual = Json.encodeToJsonElement(sampleObject1)
        assertEquals(actual.jsonObject["id"]?.jsonPrimitive?.int, sampleObject1.id)
        assertEquals(actual.jsonObject["name"]?.jsonPrimitive?.content, sampleObject1.name)
        assertEquals(
            sampleObject1.brand.name,
            actual.jsonObject["brand"]?.jsonObject?.get("name")?.jsonPrimitive?.content
        )
    }

    @Test
    fun serializeToJsonString() {
        // String に変換
        val actual = Json.encodeToString(sampleObject1)
        assertEquals(
            """{"id":1,"name":"iPhone 14","description":"An apple mobile.","price":799,"brand":{"name":"Apple"},"categories":["smartphones"]}""",
            actual
        )
    }

    @Test
    fun buildJsonObject() {
        val json = buildJsonObject {
            put("name", "aaa")
            put("age", 20)
            putJsonArray("num") {
                for (i in 0..9) {
                    add(i)
                }
            }
        }
        assertEquals("aaa", json["name"]?.jsonPrimitive?.content)
        assertEquals(20, json["age"]?.jsonPrimitive?.int)
        assertEquals((0..9).toList(), json["num"]?.jsonArray?.map { it.jsonPrimitive.int })
    }

    @Test
    @DisplayName("関数をプロパティにもつクラスの変換")
    fun hasFunctionField() {
        @Serializable
        data class C(
            val str: String,
            @Transient
            val func: (() -> Unit)? = null
        )

        val c = C("A")
        assertEquals("""{"str":"A"}""", Json.encodeToString(c))
        assertEquals(c, Json.decodeFromString("""{"str":"A"}"""))
    }

    @Test
    @DisplayName("カスタムシリアライザを用いたクラスの変換")
    fun customSerializer() {
        @Serializable
        data class C(
            val enum: DeserializableEnum
        )
        val c = C(DeserializableEnum.ONE)
        // ONE ではなく "01" に変換される
        assertEquals("""{"enum":"01"}""", Json.encodeToString(c))
        val actual = Json.decodeFromString<C>("""{"enum":"01"}""")
        // "01" が列挙子 ONE に変換される
        assertEquals(c, actual)
        assertEquals(DeserializableEnum.ONE, actual.enum)
    }
}