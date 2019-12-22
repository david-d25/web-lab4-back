package ru.david.web_lab3.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.RegistrationConfirmationDto
import ru.david.web_lab3.dto.request.RegistrationRequestDto
import ru.david.web_lab3.service.UserService

@Controller
@RequestMapping("/api/registration")
class RegistrationController @Autowired constructor(private val userService: UserService) {

    @ResponseBody
    @PostMapping("request")
    fun request(@RequestBody request: RegistrationRequestDto) =
            userService.requestRegistration(request.email, request.name, request.password)

    @ResponseBody
    @PostMapping("confirmation")
    fun confirm(@RequestBody request: RegistrationConfirmationDto) =
            userService.confirmRegistration(request.email, request.token)
}