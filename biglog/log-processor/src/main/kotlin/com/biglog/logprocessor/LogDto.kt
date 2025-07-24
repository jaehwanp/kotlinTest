package com.biglog.logprocessor

data class LogDto(
    val timestamp: String,
    val level: String,
    val endpoint: String,
    val userId: Int,
    val message: String
) {
    fun toEntity(): LogEntity {
        return LogEntity(
            null, timestamp, level, endpoint, userId, message
        )
    }
}