package com.qzce.awsjwtapi.auth.dto

import jakarta.servlet.http.Cookie

data class LoginCookieResponse (
    val accessToken: String,
    val cookie: Cookie
)