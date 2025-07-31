package com.qzce.awsjwtapi.auth

import com.qzce.awsjwtapi.auth.dto.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    // 회원 가입
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<SignupResponse> {
        authService.signup(request)
        return ResponseEntity.status(HttpStatus.OK).body(SignupResponse(request.loginId, "Sign Up Success"))
    }

    // 로그인
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse): ResponseEntity<LoginResponse> {
        val tokenPair = authService.signin(request)
        // 액세스, 리프레시 토큰 쿠키로 저장
        response.addCookie(CookieUtil.createAccessTokenCookie(tokenPair.first))
        response.addCookie(CookieUtil.createRefreshTokenCookie(tokenPair.second))

        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse("Login Success", tokenPair.first))
    }

    // access token 재발급
    @PostMapping("/token/refresh")
    fun refreshAccessToken(request: HttpServletRequest): ResponseEntity<Map<String, String>> {
        val refreshToken = request.cookies
            ?.firstOrNull { it.name == "refresh_token" }
            ?.value

        if (refreshToken.isNullOrBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(mapOf("error" to "Missing refresh token cookie"))
        }

        val newAccessToken = authService.refreshAccessToken(refreshToken)
        return ResponseEntity.ok(mapOf("accessToken" to newAccessToken))
    }

    // 로그아웃
    @PostMapping("/logout")
    fun logout(@RequestParam accountId: String, response: HttpServletResponse) {
        authService.logout(accountId)
        response.addCookie(CookieUtil.deleteAccessTokenCookie())
    }

    // 로그인 필요
    @GetMapping("/o")
    fun loginNecessary(): String {
        return "login now..."
    }

    // 로그인 불필요
    @GetMapping("/x")
    fun loginUnnecessary(): String {
        return "✅"
    }

    // google
    @PostMapping("/google-login")
    fun googleLogin(): ResponseEntity<String> {
        return ResponseEntity.ok("OK!!!")
    }
}