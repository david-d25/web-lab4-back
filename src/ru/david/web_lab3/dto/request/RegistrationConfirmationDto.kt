package ru.david.web_lab3.dto.request

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.david.web_lab3.serialization.RegistrationConfirmationDtoDeserializer

@JsonDeserialize(using = RegistrationConfirmationDtoDeserializer::class)
data class RegistrationConfirmationDto (val email: String, val token: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegistrationConfirmationDto

        if (email != other.email) return false
        if (!token.contentEquals(other.token)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + token.contentHashCode()
        return result
    }
}