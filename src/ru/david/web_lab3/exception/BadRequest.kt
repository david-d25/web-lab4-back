package ru.david.web_lab3.exception

open class BadRequest(m: String) : RuntimeException(m)

class InvalidCredentialsException : BadRequest("invalid_credentials")
class InvalidDataException : BadRequest("invalid_data")
class TokenNotFoundException : BadRequest("token_not_found")
class UserExistsException : BadRequest("user_exists")