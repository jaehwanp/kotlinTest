package com.qzce.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.qzce"])
@EnableScheduling
class AccountApplication

fun main(args: Array<String>) {
    runApplication<AccountApplication>(*args)
}
