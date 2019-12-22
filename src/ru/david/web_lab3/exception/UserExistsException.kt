package ru.david.web_lab3.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "User Already Exists")
class UserExistsException : Exception()