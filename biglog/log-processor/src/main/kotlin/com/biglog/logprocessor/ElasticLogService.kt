package com.biglog.logprocessor

import org.springframework.stereotype.Service

@Service
class ElasticLogService(private val repository: LogRepository) {
    fun save(log: LogDto) {
        repository.save(log.toEntity())
    }
}