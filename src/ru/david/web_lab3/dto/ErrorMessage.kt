package ru.david.web_lab3.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorMessage (@JsonProperty("err_msg") val message: String?)