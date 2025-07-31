package com.qzce.awsjwtapi.auth.dto

data class SignupRequest(
    val loginId: String,
    val name: String,
    val password: String,
)