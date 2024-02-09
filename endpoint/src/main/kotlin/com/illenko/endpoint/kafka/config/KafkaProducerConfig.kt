package com.illenko.endpoint.kafka.config

import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.properties.KafkaProperties
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig(private val kafkaProperties: KafkaProperties) {

    @Bean
    fun kafkaTemplate() = KafkaTemplate(producerFactory())

    @Bean
    fun producerFactory(): ProducerFactory<String, MovieResponse> {
        val configs = hashMapOf<String, Any>()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.url
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        configs[ProducerConfig.BATCH_SIZE_CONFIG] = kafkaProperties.batchSize
        return DefaultKafkaProducerFactory(configs)
    }
}