package com.qzce.chat.controller

import com.qzce.chat.dto.ChatMessage
import com.qzce.chat.dto.ChatMessageEvent
import com.qzce.chat.kafka.ChatKafkaProducer
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class ChatWebSocketController (
    private val chatKafkaProducer: ChatKafkaProducer,
) {

    // 메시지 전송
    @MessageMapping("/chat.sendMessage")
    fun sendMessageHandle(@Payload message: ChatMessage) {
        val event = ChatMessageEvent(
            messageId = UUID.randomUUID().toString(),
            roomId = message.roomId,
            senderId = message.senderId,
            receiverId =  message.receiverId,
            content = message.content,
            messageType = message.messageType,
        )

        chatKafkaProducer.send(event)
    }
}