package ru.david.web_lab3.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="person", schema="s265472")
data class User(@Id val email: String? = null, val name: String? = null, val passwordHash: ByteArray? = ByteArray(0)) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (name != other.name) return false
        if (passwordHash != null) {
            if (other.passwordHash == null) return false
            if (!passwordHash.contentEquals(other.passwordHash)) return false
        } else if (other.passwordHash != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (passwordHash?.contentHashCode() ?: 0)
        return result
    }
}