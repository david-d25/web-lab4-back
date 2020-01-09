package ru.david.web_lab3.dto.request

data class BootstrapRequest (val login: String, val token: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BootstrapRequest

        if (login != other.login) return false
        if (!token.contentEquals(other.token)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + token.contentHashCode()
        return result
    }
}