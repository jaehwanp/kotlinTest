package com.qzce.awsjwtapi.auth

import com.qzce.awsjwtapi.account.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository: JpaRepository<Account, String> {
    fun findByEmail(email: String): Account?
    fun findByKakaoId(kakaoId: Long): Account?
}