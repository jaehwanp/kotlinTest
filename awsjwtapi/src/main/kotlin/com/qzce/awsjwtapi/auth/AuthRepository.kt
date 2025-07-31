package com.qzce.awsjwtapi.auth

import com.qzce.awsjwtapi.account.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository: JpaRepository<Account, String> {
    fun findByLoginId(loginId: String): Account?
}