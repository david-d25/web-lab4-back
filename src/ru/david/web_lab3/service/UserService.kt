package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.DataBinder
import ru.david.web_lab3.entity.RegistrationToken
import ru.david.web_lab3.entity.User
import ru.david.web_lab3.exception.InvalidDataException
import ru.david.web_lab3.exception.TokenNotFoundException
import ru.david.web_lab3.exception.UserExistsException
import ru.david.web_lab3.mapping.Base64UrlEncodedMapper
import ru.david.web_lab3.provider.RegistrationTokenSecretKeyProvider
import ru.david.web_lab3.repository.RegistrationTokenRepository
import ru.david.web_lab3.repository.UserRepository
import ru.david.web_lab3.validation.RegistrationTokenValidator

@Service
open class UserService @Autowired constructor(private val userRepository: UserRepository,
                                              private val registrationTokenRepository: RegistrationTokenRepository,
                                              private val passwordService: PasswordService,
                                              private val registrationTokenSecretKeyProvider: RegistrationTokenSecretKeyProvider,
                                              private val eMailService: EMailService,
                                              private val base64UrlEncodedMapper: Base64UrlEncodedMapper,
                                              private val registrationTokenValidator: RegistrationTokenValidator,
                                              private val emailFrom: String) {

    fun requestRegistration(email: String, name: String, password: String) {
        if (userRepository.findById(email).isPresent ||registrationTokenRepository.findById(email).isPresent)
            throw UserExistsException()

        val secretKey = registrationTokenSecretKeyProvider.get()

        val token = RegistrationToken(
                email,
                name,
                passwordService.hash(password),
                secretKey)

        val dataBinder = DataBinder(token)
        dataBinder.addValidators(registrationTokenValidator)
        dataBinder.validate()

        if (dataBinder.bindingResult.hasErrors())
            throw InvalidDataException()

        eMailService.sendEmail(emailFrom, email, "<a href=\"127.0.0.1/login" +
                "?action=confirm_reg" +
                "&target=$email" +
                "&token=${base64UrlEncodedMapper.bytesToBase64UrlEncoded(secretKey)}\">CLICK</a> " +
                "here to confirm the registration")
        registrationTokenRepository.save(token)
    }

    open fun confirmRegistration(email: String, secretKey: ByteArray) {
        val tokenOptional = registrationTokenRepository.findById(email)
        if (tokenOptional.isPresent) {
            val token = tokenOptional.get()
            if (token.secretKey.contentEquals(secretKey)) {
                val user = User(token.email, token.name, token.passwordHash)
                userRepository.save(user)
                registrationTokenRepository.deleteByEmail(email)
            } else {
                throw TokenNotFoundException()
            }
        } else {
            throw TokenNotFoundException()
        }
    }
}