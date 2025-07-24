package com.biglog.logprocessor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LogConsumer(
    private val objectMapper: ObjectMapper,
    private val elasticLogService: ElasticLogService,
) {

    @KafkaListener(
        topics = ["log-topic"],
        groupId = "log-consumer-group",
    )
    fun consumeLog(message: String) {
        val log = objectMapper.readValue(message, LogDto::class.java)
        elasticLogService.save(log)
    }
}