package ru.david.web_lab3.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "point", schema = "s265472")
data class Point(
        @Id @GeneratedValue val id: Long? = null,
        val x: Float = 0F,
        val y: Float = 0F,
        val r: Float = 0F,
        val hit: Boolean = false,
        val authorName: String? = null,
        val authorEmail: String? = null,
        val created: LocalDateTime = LocalDateTime.now()
)