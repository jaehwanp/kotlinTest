package com.qzce.account.controller

import com.qzce.account.dto.LoginRequest
import com.qzce.account.dto.LoginResponse
import com.qzce.account.dto.SignUpRequest
import com.qzce.account.service.AccountService
import com.qzce.common.jwt.TokenRequest
import com.qzce.common.jwt.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<Any> {
        accountService.createAccount(request)
        return ResponseEntity.ok("회원가입 성공")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = accountService.login(request)
        return ResponseEntity.ok(LoginResponse("로그인 성공", TokenResponse(token.accessToken, token.refreshToken)))
    }

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal id: String): ResponseEntity<Void> {
        accountService.logout(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/token/refresh")
    fun refreshToken(@RequestBody request: TokenRequest): ResponseEntity<Any> {
        val token = accountService.resetRefreshToken(request)
        return ResponseEntity.ok(LoginResponse("토큰 재발급", TokenResponse(token.accessToken, token.refreshToken)))
    }
}