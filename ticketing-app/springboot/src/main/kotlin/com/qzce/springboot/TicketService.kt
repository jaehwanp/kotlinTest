package com.qzce.springboot

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.qzce.springboot.exception.DuplicateReservationException
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class TicketService(
    private val redisTemplate: StringRedisTemplate,
    private val kafkaLogProducer: KafkaLogProducer,
) {

    // 예약
    fun tryReserve(userId: String, seatId: String): Boolean {
        val seatKey = "seat:$seatId" // userId
        val userKey = "user:$userId:seat" // 좌석번호

        // 이미 예약한 사용자 (이미 redis에 userKey가 존재하면 중복오류)
        if(redisTemplate.hasKey(userKey)) {
            throw DuplicateReservationException()
        }

        // 해당 seatKey가 존재 하지않으면 실패 (동시성 제어)
        val success = redisTemplate.opsForValue().setIfAbsent(seatKey, userKey)

        /* 서버 다운시 redis lock 방지
        val result = redisTemplate.execute(
            DefaultRedisScript(luaScript(), Int::class.java),
            listOf(seatKey, userKey),
            userKey, seatKey
        )

        when (result) {
            1 -> {
                kafkaLogProducer.send(userId, seatId)
                true
            }
            0 -> throw SeatAlreadyReservedException() // 좌석 이미 점유됨
            -1 -> throw DuplicateReservationException() // 유저가 이미 예약함
            else -> throw IllegalStateException("Unexpected Redis response")
        }
        */

        if(success == true) {
            // 유저 key -> 좌석 value 역방향 저장
            redisTemplate.opsForValue().set(userKey, seatKey)

            // kafka 메시지 전송
            kafkaLogProducer.send(userId, seatId)
            return true
        }
        return false
    }

    private fun luaScript(): String {
        return """
    if redis.call("EXISTS", KEYS[2]) == 1 then
        return -1
    end
    if redis.call("EXISTS", KEYS[1]) == 1 then
        return 0
    end
    redis.call("SET", KEYS[1], ARGV[1])
    redis.call("SET", KEYS[2], ARGV[2])
    return 1
            """.trimIndent()
    }

}