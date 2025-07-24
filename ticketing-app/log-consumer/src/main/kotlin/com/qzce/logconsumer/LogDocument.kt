package com.qzce.logconsumer

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.UUID

@Document(indexName = "logs")
data class LogDocument(

    @Id
    val logId: String = UUID.randomUUID().toString(),

    @Field(type = FieldType.Text)
    val userId: String,

    @Field(type = FieldType.Text)
    val seatId: String,

    @Field(type = FieldType.Text)
    val timestamp: String
)