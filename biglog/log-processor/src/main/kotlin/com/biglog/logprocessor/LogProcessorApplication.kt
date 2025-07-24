package com.biglog.logprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogProcessorApplication

fun main(args: Array<String>) {
    runApplication<LogProcessorApplication>(*args)
}
