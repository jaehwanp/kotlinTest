package com.qzce.awsjwtapi.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class JwtRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    @Value("\${jwt.expireTime}") private val expireTime: Long,
    @Value("\${jwt.redisPrefix}") private val redisPrefix: String
) {
    // 토큰 저장
    fun save(accountId: String, token: String) {
        redisTemplate.opsForValue().set("$redisPrefix:$accountId", token, expireTime, TimeUnit.SECONDS)
    }

    // 토큰 검색
    fun searchToken(accountId: String, refreshToken: String) {
        val storedToken = redisTemplate.opsForValue().get("$redisPrefix:$accountId")
            ?: throw IllegalArgumentException("Refresh token not found")

        if(storedToken != refreshToken) {
            throw IllegalArgumentException("Token mismatch")
        }
    }

    // 토큰 삭제
    fun deleteToken(accountId: String) {
        redisTemplate.delete("$redisPrefix:$accountId")
    }

}