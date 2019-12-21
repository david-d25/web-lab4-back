package ru.david.web_lab3.dto.request

class RegistrationRequestDto(val action: Action,
                             val name: String?,
                             val email: String?,
                             val password: String?,
                             val token: String?) {
    enum class Action {
        REQUEST, CONFIRM
    }
}