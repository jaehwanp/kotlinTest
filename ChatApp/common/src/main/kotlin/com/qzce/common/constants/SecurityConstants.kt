package com.qzce.common.constants

object SecurityConstants {
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_REFRESH_TOKEN = "Refresh-Token"

    const val SECRET_KEY = "qqq99qqqqqqq9q9q9q9q99q9q9q9q99q9q99q9q9q9"
    const val ACCESS_TOKEN_VALID_TIME: Long = 1000 * 60 * 30     // 30분
    const val REFRESH_TOKEN_VALID_TIME: Long = 1000L * 60 * 60 * 24 * 7 // 7일

    const val CLAIM_ACCOUNT_ID = "accountId"
    const val CLAIM_ROLE = "role"

    const val AUTH_LOGIN_URL = "/login"
    const val AUTH_REFRESH_URL = "/token/refresh"
}