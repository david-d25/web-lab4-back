package ru.david.web_lab3.repository

import org.springframework.data.repository.CrudRepository
import ru.david.web_lab3.entity.UserToken

interface UserTokenRepository : CrudRepository<UserToken, Long>