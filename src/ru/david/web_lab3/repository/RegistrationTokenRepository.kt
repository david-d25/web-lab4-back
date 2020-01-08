package ru.david.web_lab3.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.david.web_lab3.entity.RegistrationToken

@Repository
interface RegistrationTokenRepository : CrudRepository<RegistrationToken, String>, CustomizedRegistrationToken {
    @Transactional
    fun deleteByEmail(email: String)
}