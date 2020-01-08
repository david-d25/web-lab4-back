package ru.david.web_lab3.dto.request

data class LogoutRequest (val login: String, val token: ByteArray, val fromEverywhere: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LogoutRequest

        if (login != other.login) return false
        if (!token.contentEquals(other.token)) return false
        if (fromEverywhere != other.fromEverywhere) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + token.contentHashCode()
        result = 31 * result + fromEverywhere.hashCode()
        return result
    }
}