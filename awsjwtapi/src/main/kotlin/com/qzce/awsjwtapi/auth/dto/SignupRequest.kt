package com.qzce.awsjwtapi.auth.dto

data class SignupRequest(
    val email: String,
    val name: String,
    val password: String,
)