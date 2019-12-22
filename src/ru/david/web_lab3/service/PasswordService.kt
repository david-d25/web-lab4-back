package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest


@Service
class PasswordService @Autowired constructor(private val preSalt: String,
                                             private val postSalt: String) {
    fun hash(password: String): ByteArray {
        val salted = preSalt + password + postSalt;
        val md = MessageDigest.getInstance("SHA-512")
        return md.digest(salted.toByteArray())
    }
}