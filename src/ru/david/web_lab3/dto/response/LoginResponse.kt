package ru.david.web_lab3.dto.response

data class LoginResponse (val token: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginResponse

        if (!token.contentEquals(other.token)) return false

        return true
    }

    override fun hashCode(): Int {
        return token.contentHashCode()
    }
}