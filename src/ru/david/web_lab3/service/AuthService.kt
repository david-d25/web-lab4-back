package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.david.web_lab3.dto.response.LoginResponse
import ru.david.web_lab3.entity.UserToken
import ru.david.web_lab3.exception.InvalidCredentialsException
import ru.david.web_lab3.provider.UserTokenProvider
import ru.david.web_lab3.repository.UserRepository
import ru.david.web_lab3.repository.UserTokenRepository

@Service
class AuthService @Autowired constructor(private val userTokenRepository: UserTokenRepository,
                                         private val userRepository: UserRepository,
                                         private val passwordService: PasswordService,
                                         private val userTokenProvider: UserTokenProvider) {

    fun login(email: String, password: String): LoginResponse {
        val passwordHash = passwordService.hash(password)
        val userOptional = userRepository.findById(email)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            if (user.passwordHash!!.contentEquals(passwordHash)) {
                val token = userTokenProvider.get()
                val userToken = UserToken(
                        user = user,
                        token = token
                )
                userTokenRepository.save(userToken)
                return LoginResponse(token)
            } else {
                throw InvalidCredentialsException()
            }
        } else {
            throw InvalidCredentialsException()
        }
    }

    fun logout(email: String, token: ByteArray, fromEverywhere: Boolean) {
        if (fromEverywhere)
            if (checkUserAuth(email, token))
                userTokenRepository.deleteUserTokenByUser(userRepository.findById(email).get())
        else
            userTokenRepository.deleteUserTokenByUserAndToken(userRepository.findById(email).get(), token)
    }

    fun checkUserAuth(email: String, token: ByteArray) =
        userTokenRepository.findByUserAndToken(userRepository.findById(email).get(), token).isPresent
}