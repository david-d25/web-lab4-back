package ru.david.web_lab3.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.DataBinder
import ru.david.web_lab3.dto.response.AddPointResponse
import ru.david.web_lab3.entity.Point
import ru.david.web_lab3.exception.InvalidDataException
import ru.david.web_lab3.exception.Unauthorized
import ru.david.web_lab3.repository.PointRepository
import ru.david.web_lab3.repository.UserRepository
import ru.david.web_lab3.validation.PointValidator

@Service
class PointsService @Autowired constructor(
        private val authService: AuthService,
        private val userRepository: UserRepository,
        private val pointValidator: PointValidator,
        private val pointRepository: PointRepository
) {
    fun addPoint(x: Float, y: Float, r: Float, login: String, token: ByteArray): AddPointResponse {
        if (authService.checkUserAuth(login, token)) {
            val author = userRepository.findById(login).get()
            var point = Point(
                    x = x,
                    y = y,
                    r = r,
                    hit = checkHit(x, y, r),
                    authorName = author.name,
                    authorEmail = author.email
            )

            val dataBinder = DataBinder(point)
            dataBinder.addValidators(pointValidator)
            dataBinder.validate()

            if (dataBinder.bindingResult.errorCount != 0)
                throw InvalidDataException()

            point = pointRepository.save(point)
            return AddPointResponse(point.hit, point.id!!)
        } else
            throw Unauthorized()
    }

    private fun checkHit(x: Float, y: Float, r: Float) =
            (x >= 0 && x <= r/2 && y >= 0&& y <= r) ||
                    (x > 0 && y < 0 && x*x + y*y <= r*r) ||
                    (x < 0 && y < 0 && y > -x*2 - r)
}