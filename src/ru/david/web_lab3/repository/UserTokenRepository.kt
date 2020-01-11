package ru.david.web_lab3.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.david.web_lab3.entity.User
import ru.david.web_lab3.entity.UserToken
import java.util.*

@Repository
@Transactional
interface UserTokenRepository : CrudRepository<UserToken, Long> {
    fun findByUserAndToken(user: User, token: ByteArray): Optional<UserToken>
    fun deleteUserTokenByUser(user: User)
    fun findAllByUser(user: User): List<UserToken>
}