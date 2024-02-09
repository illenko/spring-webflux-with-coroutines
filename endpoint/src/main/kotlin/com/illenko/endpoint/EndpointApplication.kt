package com.illenko.endpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients

@SpringBootApplication
@EnableReactiveFeignClients
@ConfigurationPropertiesScan("com.illenko.endpoint.properties")
class EndpointApplication

fun main(args: Array<String>) {
    runApplication<EndpointApplication>(*args)
}