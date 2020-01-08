package ru.david.web_lab3.dto.response

data class LoginResponse (val name: String, val token: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginResponse

        if (name != other.name) return false
        if (!token.contentEquals(other.token)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + token.contentHashCode()
        return result
    }
}