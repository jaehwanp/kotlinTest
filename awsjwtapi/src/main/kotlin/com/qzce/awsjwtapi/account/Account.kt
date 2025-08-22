package com.qzce.awsjwtapi.account

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Account (
    @Id
    val accountId: String = UUID.randomUUID().toString(),
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val kakaoId: Long? = null,
)