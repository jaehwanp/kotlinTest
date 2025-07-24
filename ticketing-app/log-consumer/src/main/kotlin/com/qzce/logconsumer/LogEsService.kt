package com.qzce.logconsumer

import org.springframework.stereotype.Service

@Service
class LogEsService(
    private val logEsRepository: LogEsRepository
) {
    // 로그 저장
    fun logSave(logDocument: LogDocument) {
        logEsRepository.save(logDocument)
    }

}