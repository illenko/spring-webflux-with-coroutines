package com.illenko.endpoint.config

import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
class DefaultCircuitBreakerPropertiesConfig : BeanPostProcessor {


    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is CircuitBreakerProperties) {
            bean.instances.values
                .filter { it.baseConfig == null }
                .forEach { it.baseConfig = "default" }
        }
        return bean
    }
}