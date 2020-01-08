package ru.david.web_lab3.exception.handling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.ModelAndView
import ru.david.web_lab3.dto.ErrorMessage
import ru.david.web_lab3.exception.BadRequestException

@RestControllerAdvice
class BadRequestExceptionHandler {
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: BadRequestException) = ErrorMessage(e.message)
}