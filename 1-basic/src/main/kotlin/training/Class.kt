package training

class User(
    val name: String,
    val age: Int
)

data class DataClassUser(
    val name: String,
    val age: Int
)

/**
 * https://kotlinlang.org/docs/properties.html#backing-properties
 */
data class BackingPropertyUser(
    private val _name: String?
) {
    val name: String
        get() {
            return if (_name.isNullOrEmpty()) {
                "EMPTY"
            } else {
                _name.uppercase()
            }
        }
}
