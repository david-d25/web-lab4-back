package ru.david.web_lab3.exception.handling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.david.web_lab3.dto.ErrorMessage
import ru.david.web_lab3.exception.Unauthorized

@RestControllerAdvice
class UnauthorizedHandler {
    @ExceptionHandler(Unauthorized::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handle(e: Unauthorized) = ErrorMessage(e.message)
}