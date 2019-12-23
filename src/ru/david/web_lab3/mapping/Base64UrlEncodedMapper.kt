package ru.david.web_lab3.mapping

import org.springframework.stereotype.Component
import java.util.*

@Component
class Base64UrlEncodedMapper {
    fun bytesToBase64UrlEncoded(src: ByteArray): String {
        val encoder = Base64.getEncoder()
        val encoded = encoder.encodeToString(src)
        return encoded
                .replace("+", ".")
                .replace("/", "_")
                .replace("=" , "-")
    }

    fun base64UrlEncodedToBytes(src: String): ByteArray {
        val decoder = Base64.getDecoder()
        return decoder.decode(src
                .replace(".", "+")
                .replace("_", "/")
                .replace("-", "="))
    }
}