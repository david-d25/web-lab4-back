package ru.david.web_lab3.service

import org.springframework.stereotype.Service

interface EMailService {
    fun sendEmail(from: String, to: String, htmlContent: String)
}