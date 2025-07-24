package com.qzce.springboot

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.Instant.now

@Service
class KafkaLogProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun send(userId: String, seatId: String) {
        val log = mapOf(
            "userId" to userId,
            "seatId" to seatId,
            "timestamp" to now().toString()
        )
        val json = jacksonObjectMapper().writeValueAsString(log)
        kafkaTemplate.send("ticket.reservation.logs", json)
    }
}