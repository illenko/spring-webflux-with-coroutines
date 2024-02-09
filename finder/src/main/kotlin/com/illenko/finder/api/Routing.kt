package com.illenko.finder.api

import com.illenko.finder.api.handler.SearchHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routing(private val searchHandler: SearchHandler) {

    @Bean
    fun route() = coRouter {
        GET(ENDPOINT).invoke(searchHandler::search)
    }

    companion object {
        const val ENDPOINT = "/search"
    }
}