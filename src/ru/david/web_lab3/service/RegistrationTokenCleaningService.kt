package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.david.web_lab3.repository.RegistrationTokenRepository

@Service
class RegistrationTokenCleaningService @Autowired constructor(
        private val registrationTokenRepository: RegistrationTokenRepository
) {

    @Scheduled(fixedRate = 60000)
    fun cleanUp() {
        registrationTokenRepository.cleanUpOutdatedTokens()
    }
}