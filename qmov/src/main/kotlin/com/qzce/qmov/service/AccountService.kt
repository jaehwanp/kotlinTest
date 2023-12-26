package com.qzce.qmov.service

import com.qzce.qmov.entity.Account
import com.qzce.qmov.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun getAccount(id: Long): Account {
        return accountRepository.findById(id).get()
    }

    fun createAccount(account: Account): Account? {
        account.createdAt = LocalDateTime.now()

        return accountRepository.save(account)
    }

    fun updateAccount(updatedAccount: Account): Account? {
        val account = accountRepository.findByIdOrNull(updatedAccount.id)?: throw Exception()
        return accountRepository.save(updatedAccount)
    }

    fun deleteAccount(id: Long) {
        accountRepository.deleteById(id)
    }
}