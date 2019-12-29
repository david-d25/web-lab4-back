package ru.david.web_lab3.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Data")
class InvalidDataException : Exception()