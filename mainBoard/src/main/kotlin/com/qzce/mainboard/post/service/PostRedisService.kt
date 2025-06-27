package com.qzce.mainboard.post.service

import org.springframework.data.redis.core.ScanOptions
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class PostRedisService (private val redisTemplate: StringRedisTemplate) {

    val postViewCntPrefix = "post:viewCnt"

    fun getAllPostViewCnt(): Map<Long, Long> {
        val connection = redisTemplate.connectionFactory?.connection
            ?: throw IllegalArgumentException("Redis connection is null")

        val result = mutableMapOf<Long, Long>()

        val scanOptions = ScanOptions.scanOptions()
            .match("$postViewCntPrefix:*")
            .count(10) // 한 번에 키 가져오는 양
            .build()

        connection.keyCommands()?.scan(scanOptions)?.use { cursor ->
            while (cursor.hasNext()) {
                val rawKey = cursor.next()
                val key = String(rawKey)
                val id = key.substringAfter("$postViewCntPrefix:").toLongOrNull() ?: continue
                val value = redisTemplate.opsForValue().get(key)?.toLongOrNull() ?: 0
                result[id] = value
            }
        }

        return result
    }

    fun incrementPostViewCnt(postId: Long): Long {
        val key = "$postViewCntPrefix:$postId"
        return redisTemplate.opsForValue().increment(key) ?: 0
    }

    fun getPostViewCnt(postId: Long): Long {
        val key = "$postViewCntPrefix:$postId"
        return redisTemplate.opsForValue().get(key)?.toLong() ?: 0
    }

    fun resetViewCnt(postId: Long) {
        val key = "$postViewCntPrefix:$postId"
        redisTemplate.delete(key)
    }
}