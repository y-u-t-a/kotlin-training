package training

const val SAMPLE_JSON_1 = """
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
val sampleObject1 = Product(
    id = 1,
    name = "iPhone 14",
    description = "An apple mobile.",
    price = 799,
    brand = Brand(name = "Apple"),
    categories = listOf("smartphones")
)
const val SAMPLE_JSON_2 = """
{
    "id": 2,
    "name": "Pixel 7",
    "description": "An Google mobile.",
    "price": 599,
    "brand": { "name": "Google" },
    "categories": ["smartphones"]
}
"""
val sampleObject2 = Product(
    id = 2,
    name = "Pixel 7",
    description = "An Google mobile.",
    price = 599,
    brand = Brand(name = "Google"),
    categories = listOf("smartphones")
)