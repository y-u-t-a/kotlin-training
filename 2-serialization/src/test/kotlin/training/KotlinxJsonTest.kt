package training

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals

private const val sampleJson1 = """
{
    "id": 1,
    "name": "iPhone 14",
    "description": "An apple mobile.",
    "price": 799,
    "brand": { "name": "Apple" },
    "unknownKey": "value",
    "categories": ["smartphones"]
}
"""
private val sampleObject1 = Product(
    id = 1,
    name = "iPhone 14",
    description = "An apple mobile.",
    price = 799,
    brand = Brand(name = "Apple"),
    categories = listOf("smartphones")
)
private const val sampleJson2 = """
{
    "id": 2,
    "name": "Pixel 7",
    "description": "An Google mobile.",
    "price": 599,
    "brand": { "name": "Google" },
    "categories": ["smartphones"]
}
"""
private val sampleObject2 = Product(
    id = 2,
    name = "Pixel 7",
    description = "An Google mobile.",
    price = 599,
    brand = Brand(name = "Google"),
    categories = listOf("smartphones")
)

/**
 * kotlinx-serialization-json を使って JSON シリアライズをテスト
 *
 * [Docs](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-json/kotlinx.serialization.json/)
 */
class KotlinxJsonTest {
    @Test
    fun deserializeJson() {
        val actual = Json { ignoreUnknownKeys = true }.decodeFromString<Product>(sampleJson1)
        assertEquals(sampleObject1, actual)
    }

    @Test
    fun deserializeJsonArray() {
        val jsonArray = "[$sampleJson1, $sampleJson2]"
        val actual = Json { ignoreUnknownKeys = true }.decodeFromString<List<Product>>(jsonArray)
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
                for (i in 0..9) { add(i) }
            }
        }
        assertEquals("aaa", json["name"]?.jsonPrimitive?.content)
        assertEquals(20, json["age"]?.jsonPrimitive?.int)
        assertEquals((0..9).toList(), json["num"]?.jsonArray?.map { it.jsonPrimitive.int })
    }
}