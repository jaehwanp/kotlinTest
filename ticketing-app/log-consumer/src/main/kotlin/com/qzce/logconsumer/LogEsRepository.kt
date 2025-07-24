package com.qzce.logconsumer

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface LogEsRepository: ElasticsearchRepository<LogDocument, String> {
}