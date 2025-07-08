package com.qzce.account.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Account(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @Column(unique = true)
    val loginId: String,
    val username: String,
    val password: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime
)