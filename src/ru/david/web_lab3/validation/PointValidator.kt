package ru.david.web_lab3.validation

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import ru.david.web_lab3.entity.Point

@Component
class PointValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return Point::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        target as Point

        if (target.x < -5 || target.x > 3)
            errors.reject("x")

        if (target.y <= -3 || target.y >= 3)
            errors.reject("y")

        if (target.r <= 0 || target.r > 3)
            errors.reject("r")
    }
}