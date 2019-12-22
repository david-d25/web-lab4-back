package ru.david.web_lab3.provider

interface Provider<T> {
    fun get() : T
}