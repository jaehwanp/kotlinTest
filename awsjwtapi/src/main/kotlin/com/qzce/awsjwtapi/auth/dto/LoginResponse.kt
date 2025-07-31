package com.qzce.awsjwtapi.auth.dto

import java.time.LocalDateTime

data class LoginResponse (
    val msg: String,
    val accessToken: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)