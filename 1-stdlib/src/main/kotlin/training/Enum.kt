package training

/**
 * [Docs](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/)
 */
enum class DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
}

enum class PrefCode(val code: String) {
    HOKKAIDO("01"),
    AOMORI("02"),
    IWATE("03"),
    ;
    companion object {
        fun fromCode(code: String): PrefCode {
            return PrefCode.values().firstOrNull { it.code == code }
                    ?: throw IllegalArgumentException("No enum constant.")
        }
    }
}