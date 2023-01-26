package training

import kotlinx.serialization.Serializable

@Serializable
data class Product (
    val id: Int,
    val name: String,
    val description: String = "",
    val price: Int,
    val brand: Brand, // ネストしたオブジェクト
    val categories: List<String>,
) {
    init {
        // バリデーション
        require(categories.isNotEmpty()) { "categories must be one or more." }
        require(categories.none { it.isEmpty() }) { "category cannot be empty." }
    }
}