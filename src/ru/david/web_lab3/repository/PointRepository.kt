package ru.david.web_lab3.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.david.web_lab3.entity.Point

@Repository
interface PointRepository : CrudRepository<Point, Long> {
    fun findAllByOrderByCreatedDesc(): List<Point>
}