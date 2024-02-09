package com.illenko.endpoint.api.handler

import com.illenko.endpoint.api.request.MovieRequest
import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.service.MovieService
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.util.*
import kotlin.reflect.KSuspendFunction1

@Component
class MovieHandler(private val service: MovieService) {

    private val log = KotlinLogging.logger {}

    suspend fun create(request: ServerRequest) = processRequest(request, service::create)

    suspend fun update(request: ServerRequest) = processRequest(request, service::update)

    suspend fun findById(request: ServerRequest) = singleItem(service.findById(request.getMovieId()))

    suspend fun search(request: ServerRequest) = singleItem(service.search(request.getTitle()))

    suspend fun findAll(request: ServerRequest) =
        service.findAll().let { ok().json().contentType(MediaType.APPLICATION_JSON).bodyAndAwait(it) }

    private suspend fun singleItem(response: MovieResponse?) =
        response
            ?.let { ok().json().bodyValueAndAwait(it) }
            ?: notFound().buildAndAwait()

    private suspend fun processRequest(
        request: ServerRequest,
        processingFunction: KSuspendFunction1<MovieRequest, MovieResponse>
    ) = request.awaitBodyOrNull(MovieRequest::class)!!
        .let { processingFunction.invoke(it).also { log.debug("Processed Movie: $it") } }
        .let { ok().json().bodyValueAndAwait(it) }

    fun ServerRequest.getMovieId(): UUID = UUID.fromString(pathVariable(ID))
    fun ServerRequest.getTitle(): String = queryParam(TITLE).orElse(null)

    companion object {
        const val ID = "movie_id"
        const val TITLE = "title"
    }
}