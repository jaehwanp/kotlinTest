package com.qzce.springboot.exception

import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RedisConnectionFailureException::class)
    fun redisConnectionFailHandler(ex: RedisConnectionFailureException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Redis 연결 끊김")
    }

    @ExceptionHandler(DuplicateReservationException::class)
    fun duplicateReservationHandler(ex: DuplicateReservationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("❌ 이미 예약했습니다.")
    }
}

class DuplicateReservationException: RuntimeException()