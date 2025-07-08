package com.qzce.chat.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.qzce.chat.dto.ChatMessageEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service


@Service
class ChatKafkaConsumer(
    private val messagingTemplate: SimpMessagingTemplate,
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = ["chat-message"], groupId = "chat")
    fun consume(record: ConsumerRecord<String, String>) {
        val messageJson = record.value()
        val event = objectMapper.readValue(messageJson, ChatMessageEvent::class.java)

        val receiverId = event.receiverId

        println("convertAndSendToUser receiverId: $receiverId")

        // 지금은 무조건 WebSocket으로 전송한다고 가정
//        messagingTemplate.convertAndSend("/queue/messages/${event.receiverId}", event)
        messagingTemplate.convertAndSendToUser(event.receiverId, "/queue/messages", event)

        println("[KafkaConsumer] 메시지 수신 → WebSocket 전송 완료: $event")
    }
}
