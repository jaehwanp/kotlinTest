package com.qzce.notificationservice

import com.qzce.notificationservice.dto.NotificationMessage
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class NotificationConsumer {

    @RabbitListener(queues = ["notification.queue"])
    fun receive(message: NotificationMessage) {
        println("✅도착 ${message.toAccountId} - ${message.content}")
    }
}