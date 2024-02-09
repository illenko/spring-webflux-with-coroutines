package com.illenko.endpoint.config

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker
import org.springframework.stereotype.Component
import reactivefeign.cloud2.ReactiveFeignCircuitBreakerFactory


@Component
class ClientConfig(private val reactiveResilience4JCircuitBreakerFactory: ReactiveResilience4JCircuitBreakerFactory) :
    ReactiveFeignCircuitBreakerFactory {

    override fun apply(reactiveFeignClientName: String): ReactiveCircuitBreaker? {
        val circuitBreakerId = reactiveFeignClientName.replace("[#(,]+".toRegex(), "_")
            .replace("\\)+".toRegex(), "")
            .replace("_+$".toRegex(), "")
        return reactiveResilience4JCircuitBreakerFactory.create(circuitBreakerId)
    }

}