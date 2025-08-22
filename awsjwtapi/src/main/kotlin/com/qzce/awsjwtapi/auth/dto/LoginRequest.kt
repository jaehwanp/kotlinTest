package com.qzce.awsjwtapi.auth.dto

data class LoginRequest(
    val email: String,
    val password: String,
)