package training

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = DeserializableEnumSerializer::class)
enum class DeserializableEnum(val code: String) {
    ONE("01"),
    TWO("02"),
    THREE("03"),
    OTHER("-")
    ;
    companion object {
        fun fromCode(code: String): DeserializableEnum {
            return DeserializableEnum.values()
                .firstOrNull { it.code == code }
                ?: OTHER
        }
    }
}

object DeserializableEnumSerializer : KSerializer<DeserializableEnum> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DeserializableEnum", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: DeserializableEnum) {
        val string = value.code
        encoder.encodeString(string)
    }
    override fun deserialize(decoder: Decoder): DeserializableEnum {
        val string = decoder.decodeString()
        return DeserializableEnum.fromCode(string)
    }
}