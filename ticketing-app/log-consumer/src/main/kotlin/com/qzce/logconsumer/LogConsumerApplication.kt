package com.qzce.logconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogConsumerApplication

fun main(args: Array<String>) {
    runApplication<LogConsumerApplication>(*args)
}
