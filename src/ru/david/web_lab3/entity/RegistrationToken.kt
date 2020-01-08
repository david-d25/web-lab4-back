package ru.david.web_lab3.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="reg_token", schema = "s265472")
data class RegistrationToken(@Id val email: String? = null,
                             val name: String? = null,
                             val passwordHash: ByteArray = ByteArray(0),
                             val secretKey: ByteArray = ByteArray(0),
                             val created: LocalDateTime = LocalDateTime.now()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegistrationToken

        if (email != other.email) return false
        if (name != other.name) return false
        if (!passwordHash.contentEquals(other.passwordHash)) return false
        if (!secretKey.contentEquals(other.secretKey)) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + passwordHash.contentHashCode()
        result = 31 * result + secretKey.contentHashCode()
        result = 31 * result + created.hashCode()
        return result
    }
}