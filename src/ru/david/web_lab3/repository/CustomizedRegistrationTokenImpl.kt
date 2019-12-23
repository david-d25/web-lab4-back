package ru.david.web_lab3.repository

import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

open class CustomizedRegistrationTokenImpl : CustomizedRegistrationToken {

    @PersistenceContext
    private val em: EntityManager? = null

    @Transactional
    override fun cleanUpOutdatedTokens() {
        em ?: return

        val query = em.createQuery("delete from RegistrationToken where created < :limit")
        query.setParameter("limit", LocalDateTime.now().minusHours(1))
        query.executeUpdate()
    }
}