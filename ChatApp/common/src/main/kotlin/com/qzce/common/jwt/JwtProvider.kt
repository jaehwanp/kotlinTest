package com.qzce.common.jwt

import com.qzce.common.constants.SecurityConstants
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
//    @Value("\${jwt.secret-key}") private val secretkey: String,
//    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Int,
//    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Int,
) {
    private val secretkey = SecurityConstants.SECRET_KEY
    private val accessTokenExpiration = SecurityConstants.ACCESS_TOKEN_VALID_TIME
    private val refreshTokenExpiration = SecurityConstants.REFRESH_TOKEN_VALID_TIME

    private val key = Keys.hmacShaKeyFor(secretkey.toByteArray())

    fun getAccessToken(accountId: String): String {
        return Jwts.builder()
            .setSubject(accountId) // 제목
            .setExpiration(Date(System.currentTimeMillis() + accessTokenExpiration)) // 토큰만료기한
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getRefreshToken(accountId: String): String {
        return Jwts.builder()
            .setSubject(accountId)
            .setExpiration(Date(System.currentTimeMillis() + refreshTokenExpiration)) // 토큰만료기한
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun getAccountId(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token).body.subject
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val username = getAccountId(token)
        val principal = User(username, "", listOf(SimpleGrantedAuthority("ROLE_USER")))
        return UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
    }
}