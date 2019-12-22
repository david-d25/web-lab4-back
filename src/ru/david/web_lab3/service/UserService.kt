package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.david.web_lab3.entity.RegistrationToken
import ru.david.web_lab3.exception.UserExistsException
import ru.david.web_lab3.provider.RegistrationTokenSecretKeyProvider
import ru.david.web_lab3.repository.RegistrationTokenRepository
import ru.david.web_lab3.repository.UserRepository

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository,
                                         private val registrationTokenRepository: RegistrationTokenRepository,
                                         private val passwordService: PasswordService,
                                         private val registrationTokenSecretKeyProvider: RegistrationTokenSecretKeyProvider) {

    fun requestRegistration(email: String, name: String, password: String) {
        // todo consider removing outdated registration tokens
        if (userRepository.findById(email).isPresent || registrationTokenRepository.findById(email).isPresent)
            throw UserExistsException()

        val token = RegistrationToken(
                email,
                name,
                passwordService.hash(password),
                registrationTokenSecretKeyProvider.get())
        // todo
    }

    fun confirmRegistration(email: String, password: ByteArray) {
        
    }
}