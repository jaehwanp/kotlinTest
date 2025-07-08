package com.qzce.chat.dto

import java.time.OffsetDateTime

data class ChatMessage(
    val roomId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val messageType: String = "TEXT",
    val timestamp: String = OffsetDateTime.now().toString()
)
