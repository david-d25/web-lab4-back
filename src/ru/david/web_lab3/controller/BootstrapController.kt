package ru.david.web_lab3.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.david.web_lab3.dto.request.BootstrapRequest
import ru.david.web_lab3.service.BootstrapService

@Controller
@RequestMapping("/api/bootstrap")
class BootstrapController @Autowired constructor(
        private val bootstrapService: BootstrapService
) {
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun bootstrap(@RequestBody req: BootstrapRequest) = bootstrapService.bootstrap(req.login, req.token)
}