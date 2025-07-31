package com.qzce.awsjwtapi.auth.dto

import java.time.LocalDateTime

data class SignupResponse (
    val loginId: String,
    val msg: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)