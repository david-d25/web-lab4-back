package ru.david.web_lab3.validation

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class EmailValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return String::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        target as String
        val pattern = Regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")

        if (!target.matches(pattern))
            errors.reject("user_email.invalid")
    }
}