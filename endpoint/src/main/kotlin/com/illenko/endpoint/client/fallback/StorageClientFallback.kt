package com.illenko.endpoint.client.fallback

import com.illenko.endpoint.api.request.MovieRequest
import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.client.StorageClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class StorageClientFallback: StorageClient {
    override fun search(title: String): Mono<MovieResponse> = Mono.empty()

    override fun findById(id: UUID): Mono<MovieResponse> = Mono.empty()

    override fun post(movieRequest: MovieRequest): Mono<MovieResponse> = Mono.empty()

    override fun put(movieRequest: MovieRequest): Mono<MovieResponse> = Mono.empty()

    override fun findAll(): Flux<MovieResponse> = Flux.empty()
}