package com.qzce.common.jwt

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisRefreshTokenRepository(
    private val redisTemplate: RedisTemplate<String, String>
): RefreshTokenRepository {

    private val REFRESH_TOKEN_PREFIX = "refresh_token:"

    override fun save(id: String, refreshToken: String) {
        val key = REFRESH_TOKEN_PREFIX + id
        redisTemplate.opsForValue().set(key, refreshToken)
    }

    override fun get(id: String): String? {
        val key = REFRESH_TOKEN_PREFIX + id
        return redisTemplate.opsForValue().get(key)
    }

    override fun delete(id: String) {
        val key = REFRESH_TOKEN_PREFIX + id
        redisTemplate.delete(key)
    }
}