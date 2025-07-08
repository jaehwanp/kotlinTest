package com.qzce.chat.dto

import java.time.OffsetDateTime

data class ChatNotificationEvent(
    val notificationId: String,
    val userId: String,
    val messageId: String,
    val roomId: String,
    val type: String,
    val content: String,
    val timestamp: String = OffsetDateTime.now().toString()
)