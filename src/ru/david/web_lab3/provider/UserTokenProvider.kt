package ru.david.web_lab3.provider

import org.springframework.stereotype.Component

@Component
class UserTokenProvider : RandomByteArrayProvider (128)