package training

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Jackson を使って JSON シリアライズをテスト
 *
 * [Docs](https://github.com/FasterXML/jackson-docs)
 */
class JacksonJsonTest {
    private val objectMapper = jacksonObjectMapper()
        .configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false
        )

    @Test
    fun deserializeJson() {
        val actual = objectMapper.readValue(SAMPLE_JSON_1, Product::class.java)
        assertEquals(sampleObject1, actual)
    }

    @Test
    fun deserializeJsonArray() {
        val jsonArray = "[$SAMPLE_JSON_1, $SAMPLE_JSON_2]"
        val actual = objectMapper.readerForListOf(Product::class.java).readValue<List<Product>>(jsonArray)
        assertEquals(listOf(sampleObject1, sampleObject2), actual)
    }

    @Test
    fun serializeToJsonElement() {
        val jsonString = objectMapper.writeValueAsString(sampleObject1)
        // JsonString を JsonNode に変換
        val json = objectMapper.readTree(jsonString)
        assertEquals(sampleObject1.id, json.get("id").asInt())
        assertEquals(sampleObject1.name, json.get("name").asText())
        assertEquals(sampleObject1.brand.name, json.get("brand").get("name").asText())
    }

    @Test
    fun serializeToJsonString() {
        // オブジェクトを JsonString に変換
        val actual = objectMapper.writeValueAsString(sampleObject1)
        assertEquals(
            """{"id":1,"name":"iPhone 14","description":"An apple mobile.","price":799,"brand":{"name":"Apple"},"categories":["smartphones"]}""",
            actual
        )
    }

    @Test
    fun buildJsonObject() {
        val json = objectMapper.createObjectNode().apply {
            put("name", "aaa")
            put("age", 20)
            putArray("num").apply {
                for (i in 0..9) {
                    add(i)
                }
            }
        }
        assertEquals("aaa", json.get("name").asText())
        assertEquals(20, json.get("age").asInt())
        assertEquals((0..9).toList(), json.get("num").asIterable().map { it.asInt() })
    }

    @Test
    @DisplayName("関数をプロパティにもつクラスの変換")
    fun hasFunctionField() {
        data class C(
            val str: String,
            @JsonIgnore
            val func: (() -> Unit)? = null
        )

        val c = C("A")
        assertEquals("""{"str":"A"}""", objectMapper.writeValueAsString(c))
        assertEquals(c, objectMapper.readValue("""{"str":"A"}""", C::class.java))
    }

    @Test
    @DisplayName("カスタムシリアライザを用いたクラスの変換")
    fun customSerializer() {
        data class C(
            val enum: DeserializableEnumJackson
        )
        val c = C(DeserializableEnumJackson.ONE)
        // ONE ではなく "01" に変換される
        assertEquals("""{"enum":"01"}""", objectMapper.writeValueAsString(c))
        val actual = objectMapper.readValue("""{"enum":"01"}""", C::class.java)
        assertEquals(c, actual)
        assertEquals(DeserializableEnumJackson.ONE, actual.enum)
    }
}