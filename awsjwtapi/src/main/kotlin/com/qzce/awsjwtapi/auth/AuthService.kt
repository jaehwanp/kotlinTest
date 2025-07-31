package com.qzce.awsjwtapi.auth

import com.qzce.awsjwtapi.account.Account
import com.qzce.awsjwtapi.auth.dto.LoginRequest
import com.qzce.awsjwtapi.auth.dto.SignupRequest
import com.qzce.awsjwtapi.jwt.JwtProvider
import com.qzce.awsjwtapi.jwt.JwtRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtProvider: JwtProvider,
    private val authRepository: AuthRepository,
    private val jwtRepository: JwtRepository,
    private val passwordEncoder: PasswordEncoder
) {

    // 가입
    fun signup(request: SignupRequest) {

        val account = Account(
            loginId = request.loginId,
            name = request.name,
            password = passwordEncoder.encode(request.password)
        )

        // mysql 저장
        try {
            authRepository.save(account)
        } catch(e: DataIntegrityViolationException) {
            throw IllegalArgumentException("already existed ID")
        }
    }

    // 로그인
    fun signin(request: LoginRequest): Pair<String, String> {
        val account = authRepository.findByLoginId(request.loginId) ?: throw IllegalArgumentException("Invalid")

        if(!passwordEncoder.matches(request.password, account.password)) {
            throw IllegalArgumentException("Invalid ID")
        }
        val accountId = account.accountId

        val accessToken = jwtProvider.createAccessToken(accountId)
        val refreshToken = jwtProvider.createRefreshToken(accountId)

        // redis 저장 (refresh token)
        jwtRepository.save(accountId, refreshToken)

        return Pair(accessToken, refreshToken)
    }

    // 액세스 토큰 리프레시
    fun refreshAccessToken(refreshToken: String): String {
        if(!jwtProvider.validateToken(refreshToken)) {
            throw IllegalArgumentException("Invalid refresh token")
        }
        // userId 추출
        val accountId = jwtProvider.getSubject(refreshToken)

        // Redis에서 토큰 조회
        jwtRepository.searchToken(accountId, refreshToken)

        // access token 생성
        return jwtProvider.createAccessToken(accountId)
    }

    // 로그아웃
    fun logout(accountId: String) {
        jwtRepository.deleteToken(accountId)
    }

}