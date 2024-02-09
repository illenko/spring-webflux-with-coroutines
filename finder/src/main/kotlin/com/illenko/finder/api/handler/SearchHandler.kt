package com.illenko.finder.api.handler

import com.illenko.finder.service.SearchService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class SearchHandler(private val searchService: SearchService) {

    private val log = KotlinLogging.logger {}

    suspend fun search(request: ServerRequest) =
        searchService.search(request.getMovieTitle())
            .also { log.debug("Received: $it") }
            ?.let { ok().json().bodyValueAndAwait(it) } ?: notFound().buildAndAwait()

    fun ServerRequest.getMovieTitle(): String = queryParam(TITLE).orElse(null)

    companion object {
        const val TITLE = "title"
    }
}