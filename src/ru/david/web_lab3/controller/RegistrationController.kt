package ru.david.web_lab3.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.RegistrationRequestDto

@Controller
@RequestMapping("/api/registration")
class RegistrationController {

    @PostMapping
    @ResponseBody
    fun process(@RequestBody request: RegistrationRequestDto) {

    }
}