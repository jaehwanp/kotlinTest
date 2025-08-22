package com.qzce.awsjwtapi.notification.dto

data class NotificationMessage(
    val toAccountId: String,
    val content: String
)