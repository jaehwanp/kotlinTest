package com.qzce.lambdalogin

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.security.Key

/**
 * 발급 검증
 */
@Component
class JwtProvider(
    @Value("\${jwt.secretKey}") private val secret: String
) {

    private val secretKey: Key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

    fun validateToken(token: String): Boolean =
        try {
            getClaims(token)
            true
        } catch (e: Exception) {
            false
        }

    fun getClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

    fun getUserId(token: String): String = getClaims(token).subject
}