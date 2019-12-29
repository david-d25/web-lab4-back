package ru.david.web_lab3.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.david.web_lab3.dto.request.RegistrationConfirmationDto
import ru.david.web_lab3.mapping.Base64UrlEncodedMapper

@Component
class RegistrationConfirmationDtoDeserializer @Autowired constructor(
        private val base64UrlEncodedMapper: Base64UrlEncodedMapper
) : JsonDeserializer<RegistrationConfirmationDto>() {
    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): RegistrationConfirmationDto {
        val node: JsonNode = jsonParser!!.codec.readTree(jsonParser)
        val email = node.get("email").textValue()
        val token = base64UrlEncodedMapper.base64UrlEncodedToBytes(node.get("token").textValue())
        return RegistrationConfirmationDto(email, token)
    }
}