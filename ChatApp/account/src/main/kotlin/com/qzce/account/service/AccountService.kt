package com.qzce.account.service

import com.qzce.account.dto.LoginRequest
import com.qzce.account.dto.SignUpRequest
import com.qzce.account.entity.Account
import com.qzce.common.jwt.JwtProvider
import com.qzce.common.jwt.RefreshTokenRepository
import com.qzce.common.jwt.TokenRequest
import com.qzce.common.jwt.TokenResponse
import com.qzce.account.repository.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
) {

    fun createAccount(request: SignUpRequest): Account {
        if(accountRepository.existsByLoginId(request.loginId)) {
            throw IllegalArgumentException("이미 존재하는 아이디입니다.")
        }

        return accountRepository.save(
            Account(
                loginId = request.loginId,
                username = request.username,
                password = passwordEncoder.encode(request.password),
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )
    }

    fun login(request: LoginRequest): TokenResponse {
        val account = accountRepository.findByLoginId(request.loginId)
            ?: throw IllegalArgumentException("존재하지 않는 사용자")

        if (!passwordEncoder.matches(request.password, account.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다")
        }

        return generateToken(account)
    }

    fun logout(id: String) = refreshTokenRepository.delete(id)

    fun resetRefreshToken(request: TokenRequest): TokenResponse {
        val valid = jwtProvider.validToken(request.refreshToken)
        if(!valid) throw Exception("AuthenticationException")

        val accountId = jwtProvider.getAccountId(request.refreshToken)
        val savedToken = refreshTokenRepository.get(accountId)
        if(savedToken != request.refreshToken) throw Exception("AuthenticationException")

        val account = accountRepository.findById(accountId).orElseThrow {
            IllegalArgumentException("존재하지 않는 사용자")
        }

        return generateToken(account)
    }

    private fun generateToken(account: Account): TokenResponse {
        val accessToken = jwtProvider.getAccessToken(account.id)
        val refreshToken = jwtProvider.getRefreshToken(account.id)

        refreshTokenRepository.save(account.id, refreshToken) // refreshToken in redis

        return TokenResponse(accessToken, refreshToken)
    }
}