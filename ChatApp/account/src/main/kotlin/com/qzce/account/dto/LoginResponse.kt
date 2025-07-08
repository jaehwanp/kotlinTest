package com.qzce.account.dto

import com.qzce.common.jwt.TokenResponse

data class LoginResponse (
    val msg: String,
    val tokenResponse: TokenResponse,
)
