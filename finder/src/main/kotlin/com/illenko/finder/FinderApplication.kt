package com.illenko.finder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan("com.illenko.finder.properties")
class FinderApplication

fun main(args: Array<String>) {
    runApplication<FinderApplication>(*args)
}