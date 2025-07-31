package com.qzce.awsjwtapi.auth.dto

data class LoginRequest(
    val loginId: String,
    val password: String,
)