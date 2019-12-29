package ru.david.web_lab3.validation

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class UserNameValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return String::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        target as String
        if (!target.matches(Regex("[A-Za-zА-Яа-я]{2,50}")))
            errors.reject("user_name.invalid")
    }
}