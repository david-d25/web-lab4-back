package ru.david.web_lab3.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.LoginRequest
import ru.david.web_lab3.dto.request.LogoutRequest
import ru.david.web_lab3.service.AuthService

@Controller
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(private val authService: AuthService) {
    @ResponseBody
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody req: LoginRequest) = authService.login(req.login, req.password)

    @ResponseBody
    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    fun logout(@RequestBody req: LogoutRequest) = authService.logout(req.login, req.token, req.fromEverywhere)
}