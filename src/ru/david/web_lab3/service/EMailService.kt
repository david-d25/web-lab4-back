package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.*

@Service
class EMailService @Autowired constructor(private val mailSession: Session,
                                          private val emailFrom: String) {

    fun sendEmail(to: String, htmlContent: String) {
        val message = MimeMessage(mailSession)
        message.setFrom(emailFrom)
        message.setRecipient(Message.RecipientType.TO, InternetAddress(to))
        message.setContent(htmlContent, "text/html; charset=utf-16")

        Transport.send(message)
    }
}