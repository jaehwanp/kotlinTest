package com.qzce.qmov.controller

import com.qzce.qmov.entity.Account
import com.qzce.qmov.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(
    val accountService: AccountService
) {

    @GetMapping("")
    fun join(): String {
        return ""
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): Account? {
        return accountService.getAccount(id)
    }

    @PostMapping("")
    fun create(@RequestBody account: Account): Account? {
        return accountService.createAccount(account)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody account: Account) {
        // ...
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        accountService.deleteAccount(id)
    }

}