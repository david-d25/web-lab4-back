package ru.david.web_lab3.dto.request

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.david.web_lab3.serialization.UrlFriendlyBase64Deserializer

data class RegistrationConfirmation (
        val email: String,
        @JsonDeserialize(using = UrlFriendlyBase64Deserializer::class) val token: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegistrationConfirmation

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