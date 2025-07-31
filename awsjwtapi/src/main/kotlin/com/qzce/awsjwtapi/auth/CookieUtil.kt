package com.qzce.awsjwtapi.auth

import jakarta.servlet.http.Cookie

object CookieUtil {
    fun createAccessTokenCookie(accessToken: String): Cookie {
        return Cookie("access_token", accessToken).apply {
            path = "/"
            maxAge = 3600
            isHttpOnly = true
//            secure = true // HTTPS 환경
        }
    }

    fun createRefreshTokenCookie(refreshToken: String): Cookie {
        return Cookie("refresh_token", refreshToken).apply {
            path = "/"
            maxAge = 60 * 60 * 24 * 7
            isHttpOnly = true
        }
    }

    fun deleteAccessTokenCookie(): Cookie {
        return Cookie("access_token", "").apply {
            path = "/"
            maxAge = 0
            isHttpOnly = true
        }
    }

    fun deleteRefreshTokenCookie(): Cookie {
        return Cookie("refresh_token", "").apply {
            path = "/"
            maxAge = 0
            isHttpOnly = true
        }
    }

}