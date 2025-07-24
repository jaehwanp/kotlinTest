package com.biglog.logprocessor

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface LogRepository: ElasticsearchRepository<LogEntity, String> {
}