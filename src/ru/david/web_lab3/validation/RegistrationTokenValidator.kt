package ru.david.web_lab3.validation

import org.springframework.stereotype.Service
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import ru.david.web_lab3.entity.RegistrationToken

@Service
class RegistrationTokenValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return RegistrationToken::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}