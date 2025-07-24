package com.qzce.logconsumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class LogKafkaConsumer(
    private val logEsService: LogEsService
) {
    @KafkaListener(topics = ["ticket.reservation.logs"], groupId = "log-group")
    fun consume(message: String) {
        // kotlin 기본 ObjectMapper
        val msg = jacksonObjectMapper().readValue<Map<String, String>>(message)
        println("🟢 로그 수신: user=${msg["userId"]}, seat=${msg["seatId"]}, time=${msg["timestamp"]}")

        // elasticsearch 직접 저장
        // logEsSave(msg)
    }

    // 로그저장
    private fun logEsSave(msg: Map<String, String>) {
        val logDocument = jacksonObjectMapper().convertValue(msg, LogDocument::class.java)
        logEsService.logSave(logDocument)
    }

}