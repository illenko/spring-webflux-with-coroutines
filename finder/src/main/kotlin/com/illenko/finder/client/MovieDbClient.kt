package com.illenko.finder.client

import com.illenko.finder.domain.Movie
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Mono

interface MovieDbClient {

    @RequestMapping
    fun getByTitle(@RequestParam("t") title: String): Mono<Movie>
}