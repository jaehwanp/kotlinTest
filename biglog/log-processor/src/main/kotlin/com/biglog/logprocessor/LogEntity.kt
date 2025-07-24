package com.biglog.logprocessor

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "logs")
data class LogEntity(
    @Id
    val id: String? = null,
    val timestamp: String,
    val level: String,
    val endpoint: String,
    val userId: Int,
    val message: String
)