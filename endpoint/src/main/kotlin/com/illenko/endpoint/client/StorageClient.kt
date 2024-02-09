package com.illenko.endpoint.client

import com.illenko.endpoint.api.request.MovieRequest
import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.client.fallback.StorageClientFallback
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@ReactiveFeignClient(name = "storage", fallback = StorageClientFallback::class)
interface StorageClient {

    @GetMapping("/movies/search")
    fun search(@RequestParam title: String): Mono<MovieResponse>

    @GetMapping("/movies/{id}")
    fun findById(@RequestParam id: UUID): Mono<MovieResponse>

    @PostMapping(value = ["/movies"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(movieRequest: MovieRequest): Mono<MovieResponse>

    @PutMapping(value = ["/movies"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun put(movieRequest: MovieRequest): Mono<MovieResponse>

    @GetMapping("/movies")
    fun findAll(): Flux<MovieResponse>
}