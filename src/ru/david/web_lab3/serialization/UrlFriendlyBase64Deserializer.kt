package ru.david.web_lab3.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.david.web_lab3.mapping.Base64UrlEncodedMapper

@Component
class UrlFriendlyBase64Deserializer @Autowired constructor(
        private val base64UrlEncodedMapper: Base64UrlEncodedMapper
) : JsonDeserializer<ByteArray>() {
    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): ByteArray {
        val node: JsonNode = jsonParser!!.codec.readTree(jsonParser)
        return base64UrlEncodedMapper.base64UrlEncodedToBytes(node.textValue())
    }
}