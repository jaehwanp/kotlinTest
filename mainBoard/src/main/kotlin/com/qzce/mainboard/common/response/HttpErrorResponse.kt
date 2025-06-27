package com.qzce.mainboard.common.response

import org.springframework.http.HttpStatus

data class HttpErrorResponse (
    val code: Int,
    val message: String,
)