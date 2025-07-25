package com.qzce.account.repository

import com.qzce.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, String> {
    fun findByLoginId(loginId: String): Account?
    fun existsByLoginId(loginId: String): Boolean
}