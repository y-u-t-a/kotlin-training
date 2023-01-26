package training

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main() {
    val json = """
    {
      "id": 1,
      "name": "iPhone 9",
      "description": "An apple mobile which is nothing like apple",
      "price": 549,
      "brand": { "name": "Apple" },
      "unknownKey": "value",
      "categories": ["smartphones"]
    }
    """.trimIndent()
    println(Json { ignoreUnknownKeys = true }.decodeFromString<Product>(json))
}
