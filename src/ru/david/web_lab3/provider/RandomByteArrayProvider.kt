package ru.david.web_lab3.provider

import kotlin.random.Random

open class RandomByteArrayProvider(private val size: Int) : Provider<ByteArray> {
    override fun get(): ByteArray {
        val random = Random(System.currentTimeMillis())
        return random.nextBytes(size)
    }
}