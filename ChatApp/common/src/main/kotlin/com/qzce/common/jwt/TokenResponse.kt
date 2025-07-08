package com.qzce.common.jwt

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)