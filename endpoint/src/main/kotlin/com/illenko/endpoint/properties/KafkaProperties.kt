package com.illenko.endpoint.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "endpoint.kafka")
class KafkaProperties(val url: String, val batchSize: Int)