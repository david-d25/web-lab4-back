package ru.david.web_lab3.entity

import javax.persistence.*

@Entity
@Table(name="person_token", schema = "s265472")
data class UserToken(@Id @GeneratedValue val id: Long? = null,
                     @ManyToOne val user: User? = null,
                     val token: ByteArray? = ByteArray(0)) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserToken

        if (id != other.id) return false
        if (user != other.user) return false
        if (token != null) {
            if (other.token == null) return false
            if (!token.contentEquals(other.token)) return false
        } else if (other.token != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (token?.contentHashCode() ?: 0)
        return result
    }
}