package ru.david.web_lab3.dto.request

data class AddPointRequest (val x: Float, val y: Float, val r: Float, val login: String, val token: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddPointRequest

        if (x != other.x) return false
        if (y != other.y) return false
        if (r != other.r) return false
        if (login != other.login) return false
        if (!token.contentEquals(other.token)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + r.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + token.contentHashCode()
        return result
    }
}