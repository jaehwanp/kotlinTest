package com.qzce.awsjwtapi.account

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Account (
    @Id
    val accountId: String = UUID.randomUUID().toString(),

    val loginId: String,
    val name: String,
    val password: String,

)