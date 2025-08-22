package com.qzce.awsjwtapi.auth.social

import com.qzce.awsjwtapi.auth.dto.TokenResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/kakao")
class KakaoAuthController(
    private val kakaoAuthService: KakaoAuthService
) {
    @GetMapping("/login")
    fun redirectToKakaoLogin(response: HttpServletResponse) {
        val kakaoLoginUrl = kakaoAuthService.getKakaoLoginUrl()
        response.sendRedirect(kakaoLoginUrl)
    }

    @GetMapping("/callback")
    fun kakaoCallback(@RequestParam code: String): ResponseEntity<TokenResponse> {
        val tokenResponse = kakaoAuthService.loginWithKakao(code)
        return ResponseEntity.ok(tokenResponse)
    }


}