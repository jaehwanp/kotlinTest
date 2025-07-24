package com.qzce.springboot

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class TicketController(
    private val ticketService: TicketService
) {
    @GetMapping
    fun hello(): String {
        return "hello"
    }

    @PostMapping("/reserve")
    fun reserve(@RequestBody request: TicketRequest): ResponseEntity<String> {
        return if(ticketService.tryReserve(request.userId, request.seatId)) {
            ResponseEntity.status(200).body("✅ 예약 성공")
        } else {
            ResponseEntity.status(401).body("❌ 예약 실패")
        }
    }
}