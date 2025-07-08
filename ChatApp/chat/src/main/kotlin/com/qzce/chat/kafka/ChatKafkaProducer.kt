package com.qzce.chat.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.qzce.chat.dto.ChatMessageEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ChatKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
){

    fun send(message: ChatMessageEvent) {
        val json = objectMapper.writeValueAsString(message)
        kafkaTemplate.send("chat-message", message.roomId, json)
        println("[KafkaProducer] 메시지 전송 완료: $json")
    }
}