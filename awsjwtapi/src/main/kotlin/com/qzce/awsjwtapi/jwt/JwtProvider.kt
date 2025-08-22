package com.qzce.awsjwtapi.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider(
    @Value("\${jwt.secretKey}") private val secretKey: String
) {
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    // 토큰 String return
    private fun getToken(accountId: String?, expireTime: Int): String {
        return Jwts.builder()
            .setSubject(accountId)
            .setExpiration(Date(System.currentTimeMillis() + expireTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    // 액세스 토큰 발급
    fun createAccessToken(accountId: String?): String {
        return getToken(accountId, 1000 * 60 * 30)
    }

    // 리프레쉬 토큰 발급
    fun createRefreshToken(accountId: String?): String {
        return getToken(accountId, 1000 * 60 * 60 * 24 * 7)
    }

    // 토큰으로 유저아이디 조회
    fun getAccountId(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build()
            .parseClaimsJws(token).body.subject
    }

    // 토큰 validation
    fun validateToken(token: String): Boolean {
        // parseClaimsJws : 토큰 구조, 서명, expireTime, 파싱오류 검사
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    // subject 조회
    fun getSubject(refreshToken: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).body.subject
    }

}