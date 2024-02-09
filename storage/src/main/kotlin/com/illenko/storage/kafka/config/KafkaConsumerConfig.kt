package com.illenko.storage.kafka.config

import com.illenko.storage.domain.dto.MovieDto
import com.illenko.storage.properties.KafkaProperties
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaConsumerConfig(private val kafkaProperties: KafkaProperties) {

    @Bean
    fun movieKafkaFactory(): ConcurrentKafkaListenerContainerFactory<String, MovieDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, MovieDto>()
        factory.consumerFactory = movieConsumerFactory()
        return factory
    }

    @Bean
    fun movieConsumerFactory(): ConsumerFactory<String, MovieDto> {
        val configs = hashMapOf<String, Any>()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.url
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        configs[JsonDeserializer.VALUE_DEFAULT_TYPE] = MovieDto::class.java.name;
        configs[JsonDeserializer.USE_TYPE_INFO_HEADERS] = false
        configs[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        return DefaultKafkaConsumerFactory(configs)
    }
}