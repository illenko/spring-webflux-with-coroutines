package com.illenko.endpoint.client

import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.client.fallback.FinderClientFallback
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "finder", fallback = FinderClientFallback::class)
interface FinderClient {
    @GetMapping("/search")
    fun search(@RequestParam title: String): Mono<MovieResponse>
}