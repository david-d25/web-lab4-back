package ru.david.web_lab3.service

interface EMailService {
    fun sendEmail(from: String, to: String, htmlContent: String)
}