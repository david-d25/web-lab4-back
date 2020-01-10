package ru.david.web_lab3.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.AddPointRequest
import ru.david.web_lab3.entity.Point
import ru.david.web_lab3.repository.PointRepository
import ru.david.web_lab3.service.PointsService

@Controller
@RequestMapping("/api/points")
class PointsController @Autowired constructor(
        private val pointsService: PointsService,
        private val pointRepository: PointRepository
) {
    @PutMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun addPoint(@RequestBody req: AddPointRequest) = pointsService.addPoint(req.x, req.y, req.r, req.login, req.token)

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getPoints(): Iterable<Point> = pointRepository.findAllByOrderByCreatedDesc()
}