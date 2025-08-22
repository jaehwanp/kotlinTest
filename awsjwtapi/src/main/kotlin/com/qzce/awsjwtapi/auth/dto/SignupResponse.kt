package com.qzce.awsjwtapi.auth.dto

import java.time.LocalDateTime

data class SignupResponse (
    val email: String,
    val msg: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)