package com.illenko.endpoint.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.timelimiter.TimeLimiterRegistry
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CircuitBreakerConfig(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    private val timeLimiterRegistry: TimeLimiterRegistry
) {

    @Bean
    fun reactiveResilience4JCircuitBreakerFactory(): ReactiveResilience4JCircuitBreakerFactory {
        val reactiveResilience4JCircuitBreakerFactory = ReactiveResilience4JCircuitBreakerFactory()
        reactiveResilience4JCircuitBreakerFactory.configureCircuitBreakerRegistry(circuitBreakerRegistry)
        reactiveResilience4JCircuitBreakerFactory.configureDefault { createResilience4JCircuitBreakerConfiguration(it) }
        return reactiveResilience4JCircuitBreakerFactory
    }

    private fun createResilience4JCircuitBreakerConfiguration(id: String): Resilience4JCircuitBreakerConfiguration? {
        val circuitBreaker: CircuitBreaker = circuitBreakerRegistry.circuitBreaker(id)
        val circuitBreakerConfig = circuitBreaker.circuitBreakerConfig
        val timeLimiterConfig = timeLimiterRegistry.timeLimiter(id).timeLimiterConfig
        circuitBreaker.eventPublisher.onEvent { println("Circuit-breaker Event Publisher : $it") }
        return Resilience4JConfigBuilder(id).circuitBreakerConfig(circuitBreakerConfig)
            .timeLimiterConfig(timeLimiterConfig).build()
    }
}