package com.qzce.springboot

import java.time.Instant

data class ReservationResponse(
    val userId: String,
    val seatId: String,
    val timestamp: String
)