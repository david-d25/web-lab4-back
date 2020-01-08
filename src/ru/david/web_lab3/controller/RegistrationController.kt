package ru.david.web_lab3.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.RegistrationConfirmation
import ru.david.web_lab3.dto.request.RegistrationRequest
import ru.david.web_lab3.service.UserService

@Controller
@RequestMapping("/api/registration")
class RegistrationController @Autowired constructor(private val userService: UserService) {

    @ResponseBody
    @PostMapping("request")
    @ResponseStatus(value = HttpStatus.OK)
    fun request(@RequestBody request: RegistrationRequest) =
            userService.requestRegistration(request.email, request.name, request.password)

    @ResponseBody
    @PostMapping("confirmation")
    @ResponseStatus(value = HttpStatus.OK)
    fun confirm(@RequestBody request: RegistrationConfirmation) =
            userService.confirmRegistration(request.email, request.token)
}