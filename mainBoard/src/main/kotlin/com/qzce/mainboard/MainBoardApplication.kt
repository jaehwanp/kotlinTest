package com.qzce.mainboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MainBoardApplication

fun main(args: Array<String>) {
    runApplication<MainBoardApplication>(*args)
}
