package com.qzce.lambdalogin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class TestController {
    @GetMapping("/test")
    fun me(principal: Principal): String =
        "Hello, ${principal.name}"
}