package com.qzce.springboot

import com.qzce.springboot.exception.DuplicateReservationException
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@ExtendWith(MockKExtension::class)
class TicketServiceTest {

    @MockK
    lateinit var redisTemplate: StringRedisTemplate
    @MockK
    lateinit var valueOps: ValueOperations<String, String>
    private lateinit var ticketService: TicketService

    @BeforeEach
    fun setup() {
        //ticketService = TicketService(redisTemplate)
    }

//    @Test
//    fun success() {
//        every { redisTemplate.hasKey("user:A1:seat") } returns false
//        val result = ticketService.tryReserve("user1", "A1")
//        assertTrue(result)
//        verify {
//            redisTemplate.hasKey("user:A1:seat")
//        }
//    }

    @Test
    fun success() {
        // get
        val userId = "user1"
        val seatId = "A1"
        val userKey = "user:$userId:seat"
        val seatKey = "seat:$seatId"

        val valueOps = mockk<ValueOperations<String, String>>(relaxed = true)

        // 필요한 모든 호출 stub 설정
        every { redisTemplate.opsForValue() } returns mockk()
        every { redisTemplate.hasKey(userKey) } returns false
        every { valueOps.setIfAbsent(seatKey, userKey) } returns true

        val result = ticketService.tryReserve(userId, seatId)

        assertTrue(result)

        verify {
            redisTemplate.hasKey("user:user1:seat")
            redisTemplate.opsForValue()
            valueOps.setIfAbsent("seat:A1", "user:user1:seat")
        }
    }

    @Test
    fun duplicate() {
        every { redisTemplate.hasKey("user:user1:seat") } returns true

        assertFailsWith<DuplicateReservationException> {
            ticketService.tryReserve("user1", "A1")
        }

        verify { redisTemplate.hasKey("user:user1:seat") }
    }

    @Test
    fun alreadyReserved() {
        every { redisTemplate.hasKey("user:user1:seat") } returns false
        every { valueOps.setIfAbsent("seat:A1", "user1") } returns false

        val result = ticketService.tryReserve("user1", "A1")

        assertFalse(result)
    }

}