package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.david.web_lab3.dto.response.BootstrapResponse
import ru.david.web_lab3.exception.Unauthorized
import ru.david.web_lab3.repository.UserRepository

@Service
class BootstrapService @Autowired constructor(
        private val authService: AuthService,
        private val userRepository: UserRepository
) {
    fun bootstrap(login: String, token: ByteArray): BootstrapResponse {
        if (authService.checkUserAuth(login, token)) {
            val userOptional = userRepository.findById(login)
            return BootstrapResponse(userOptional.get().name!!)
        } else {
            throw Unauthorized()
        }
    }
}