package ru.david.web_lab3.repository

interface CustomizedRegistrationToken {
    fun cleanUpOutdatedTokens()
}