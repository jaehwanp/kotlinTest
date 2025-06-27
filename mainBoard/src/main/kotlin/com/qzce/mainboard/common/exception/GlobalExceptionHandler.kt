package com.qzce.mainboard.common.exception

import com.qzce.mainboard.common.response.HttpErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handle(ex: NoSuchElementException): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity(HttpErrorResponse(401, "존재하지 않는 글입니다."), HttpStatus.BAD_REQUEST)
    }

    // 유효성검사
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(ex: MethodArgumentNotValidException): ResponseEntity<HttpErrorResponse> {
        val errorMessage = ex.bindingResult.allErrors[0].defaultMessage ?: "유효성 검사 실패"
        val errorResponse = HttpErrorResponse(400, errorMessage)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // 400 에러
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity(HttpErrorResponse(400, ex.message ?: "잘못된 접근입니다."), HttpStatus.BAD_REQUEST)
    }

    // 404 에러
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFound(ex: NoHandlerFoundException): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity(HttpErrorResponse(404, "요청하신 URL을 찾을 수 없습니다."), HttpStatus.NOT_FOUND)
    }

    // 405 에러
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleNotFound(ex: HttpRequestMethodNotSupportedException): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity(HttpErrorResponse(405, "허용되지 않는 방법입니다."), HttpStatus.METHOD_NOT_ALLOWED)
    }

    // 500 에러
    @ExceptionHandler(Exception::class)
    fun handleServerError(ex: Exception): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity(HttpErrorResponse(500, "서버 오류가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}