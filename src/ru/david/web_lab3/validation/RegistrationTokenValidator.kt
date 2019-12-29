package ru.david.web_lab3.validation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import ru.david.web_lab3.entity.RegistrationToken

@Component
class RegistrationTokenValidator @Autowired constructor(
        private val userNameValidator: UserNameValidator,
        private val emailValidator: EmailValidator
) : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return RegistrationToken::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        target as RegistrationToken

        target.name ?: run {
            errors.reject("user_name.null")
            return
        }

        target.email ?: run {
            errors.reject("user_email.null")
            return
        }

        userNameValidator.validate(target.name, errors)
        emailValidator.validate(target.email, errors)
    }
}