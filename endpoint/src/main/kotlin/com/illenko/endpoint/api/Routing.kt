package com.illenko.endpoint.api

import com.illenko.endpoint.api.handler.MovieHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routing(private val movieHandler: MovieHandler) {

    @Bean
    fun route() = coRouter {
        "/movies".nest {
            GET("", movieHandler::findAll)
            GET("/search", movieHandler::search)
            GET("/{movie_id}", movieHandler::findById)

            accept(MediaType.APPLICATION_JSON).nest {
                POST("", movieHandler::create)
                PUT("", movieHandler::update)
            }
        }
    }
}