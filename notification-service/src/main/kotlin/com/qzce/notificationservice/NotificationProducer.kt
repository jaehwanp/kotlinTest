package com.qzce.notificationservice

import com.qzce.notificationservice.dto.NotificationMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class NotificationProducer(
    private val rabbitTemplate: RabbitTemplate
) {
    fun send(message: NotificationMessage) {
        rabbitTemplate.convertAndSend("notification.exchange", "notification.key", message)
    }
}