package com.illenko.storage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@ConfigurationPropertiesScan("com.illenko.storage.properties")
class StorageApplication

fun main(args: Array<String>) {
    runApplication<StorageApplication>(*args)
}