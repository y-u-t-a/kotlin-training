package training

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer

@JsonDeserialize(using = DeserializableEnumJacksonDeserializer::class)
@JsonSerialize(using = DeserializableEnumJacksonSerializer::class)
enum class DeserializableEnumJackson(val code: String) {
    ONE("01"),
    TWO("02"),
    THREE("03"),
    OTHER("-")
    ;
    companion object {
        fun fromCode(code: String): DeserializableEnumJackson {
            return DeserializableEnumJackson.values()
                .firstOrNull { it.code == code }
                ?: OTHER
        }
    }
}

object DeserializableEnumJacksonSerializer : StdSerializer<DeserializableEnumJackson>(DeserializableEnumJackson::class.java) {
    override fun serialize(
        value: DeserializableEnumJackson?,
        gen: JsonGenerator?,
        provider: SerializerProvider?
    ) {
        gen?.writeString(value?.code)
    }
}

object DeserializableEnumJacksonDeserializer : StdDeserializer<DeserializableEnumJackson>(DeserializableEnumJackson::class.java) {
    override fun deserialize(
        p: JsonParser?,
        ctxt: DeserializationContext?
    ): DeserializableEnumJackson {
        val jsonNode = p?.codec?.readTree<JsonNode>(p)
        val code = jsonNode?.asText() ?: ""
        return DeserializableEnumJackson.fromCode(code)
    }
}