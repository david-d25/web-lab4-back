package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sendinblue.ApiClient
import sendinblue.auth.ApiKeyAuth
import sibApi.SmtpApi
import sibModel.CreateSmtpEmail
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailSender
import sibModel.SendSmtpEmailTo


@Service
class EMailServiceImpl @Autowired constructor(mailApiKey: String) : EMailService {

    private var apiInstance: SmtpApi

    init {
        val defaultClient: ApiClient = sendinblue.Configuration.getDefaultApiClient()
        val apiKey = defaultClient.getAuthentication("api-key") as ApiKeyAuth
        apiKey.apiKey = mailApiKey
        apiInstance = SmtpApi()
    }

    override fun sendEmail(from: String, to: String, htmlContent: String) {
        val sendSmtpEmail = SendSmtpEmail()
        sendSmtpEmail.subject = "Registration"
        sendSmtpEmail.htmlContent = htmlContent

        val sendSmtpEmailTo = SendSmtpEmailTo()
        sendSmtpEmailTo.email = to

        sendSmtpEmail.to = listOf(sendSmtpEmailTo)

        val sender = SendSmtpEmailSender()
        sender.email = from

        sendSmtpEmail.sender = sender

        val result: CreateSmtpEmail = apiInstance.sendTransacEmail(sendSmtpEmail)
        println(result)
    }
}