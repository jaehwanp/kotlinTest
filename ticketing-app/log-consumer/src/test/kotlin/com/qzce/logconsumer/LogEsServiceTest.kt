package com.qzce.logconsumer

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LogEsServiceTest {
    private lateinit var logRepository: LogEsRepository
    private lateinit var logService: LogEsService

    @BeforeEach
    fun init() {
        logRepository = mockk(relaxed = true)
        logService = LogEsService(logRepository)
    }

    @Test
    fun successSave() {
        // given
        val log = LogDocument(
            userId = "user1",
            seatId = "A1",
            timestamp = "2020-01-02T02:00:00Z"
        )
        // stub
        every { logRepository.save(any()) } returns mockk()
        //when
        logService.logSave(log)
        //then
        verify(exactly = 1) {
            logRepository.save(log)
        }
    }

}