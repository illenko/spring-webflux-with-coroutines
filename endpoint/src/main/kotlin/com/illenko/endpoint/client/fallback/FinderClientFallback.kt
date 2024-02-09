package com.illenko.endpoint.client.fallback

import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.client.FinderClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class FinderClientFallback: FinderClient {

    override fun search(title: String): Mono<MovieResponse> {
        return Mono.empty()
    }
}