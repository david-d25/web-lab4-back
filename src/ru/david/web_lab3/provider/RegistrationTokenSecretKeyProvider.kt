package ru.david.web_lab3.provider

import org.springframework.stereotype.Component

@Component
class RegistrationTokenSecretKeyProvider : RandomByteArrayProvider(64)