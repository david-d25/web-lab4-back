package ru.david.web_lab3.exception

open class BadRequestException(m: String) : RuntimeException(m)

class InvalidCredentialsException : BadRequestException("invalid_credentials")
class InvalidDataException : BadRequestException("invalid_data")
class TokenNotFoundException : BadRequestException("token_not_found")
class UserExistsException : BadRequestException("user_exists")