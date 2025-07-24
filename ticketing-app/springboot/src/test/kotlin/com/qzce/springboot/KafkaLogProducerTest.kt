package com.qzce.springboot

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.kafka.core.KafkaTemplate

@ExtendWith(MockKExtension::class)
class KafkaLogProducerTest {

    @MockK
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @InjectMockKs
    lateinit var kafkaLogProducer: KafkaLogProducer

    @Test
    fun successSend() {
        //given
        val userId = "user1"
        val seatId = "A1"
        val topic = "ticket.reservation.logs"
        // stub (없이 실행할 경우 kafka 연결 시도 -> 실패)
        every { kafkaTemplate.send(any(), any()) } returns mockk()
        // when
        kafkaLogProducer.send(userId, seatId)
        // then
        verify(exactly = 1) {
            kafkaTemplate.send(
                topic,
                match {
                    it.contains(userId) && it.contains(seatId)
                }
            )
        }
    }
}