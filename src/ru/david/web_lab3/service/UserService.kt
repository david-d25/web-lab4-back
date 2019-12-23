package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.DataBinder
import ru.david.web_lab3.entity.RegistrationToken
import ru.david.web_lab3.exception.InvalidDataException
import ru.david.web_lab3.exception.UserExistsException
import ru.david.web_lab3.mapping.Base64UrlEncodedMapper
import ru.david.web_lab3.provider.RegistrationTokenSecretKeyProvider
import ru.david.web_lab3.repository.RegistrationTokenRepository
import ru.david.web_lab3.repository.UserRepository
import ru.david.web_lab3.validation.RegistrationTokenValidator

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository,
                                         private val registrationTokenRepository: RegistrationTokenRepository,
                                         private val passwordService: PasswordService,
                                         private val registrationTokenSecretKeyProvider: RegistrationTokenSecretKeyProvider,
                                         private val eMailService: EMailService,
                                         private val base64UrlEncodedMapper: Base64UrlEncodedMapper,
                                         private val registrationTokenValidator: RegistrationTokenValidator) {

    fun requestRegistration(email: String, name: String, password: String) {
        if (userRepository.findById(email).isPresent || registrationTokenRepository.findById(email).isPresent)
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

        registrationTokenRepository.save(token)
        eMailService.sendEmail(email, "<a href=\"127.0.0.1/confirm-reg?target=$email" +
                "&token=${base64UrlEncodedMapper.bytesToBase64UrlEncoded(secretKey)}\">CLICK</a> " +
                "here to confirm registration")
    }

    fun confirmRegistration(email: String, password: ByteArray) {

    }
}