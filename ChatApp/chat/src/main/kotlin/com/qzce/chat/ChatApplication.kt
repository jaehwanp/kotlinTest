package com.qzce.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.qzce"])
@EnableScheduling
class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
