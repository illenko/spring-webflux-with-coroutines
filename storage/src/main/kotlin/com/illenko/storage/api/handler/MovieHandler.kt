package com.illenko.storage.api.handler

import com.illenko.storage.domain.dto.MovieDto
import com.illenko.storage.service.MovieService
import kotlinx.coroutines.flow.onEach
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

    suspend fun findById(request: ServerRequest) =
        service.findById(request.getMovieId())
            ?.also { log.debug("Found Movie: $it") }
            ?.let { ok().json().bodyValueAndAwait(it) } ?: notFound().buildAndAwait()

    suspend fun search(request: ServerRequest) =
        service.search(request.getTitle()).also { log.debug("Found Movie: $it") }
            ?.let { ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it) }
            ?: notFound().buildAndAwait()

    suspend fun findAll(request: ServerRequest) =
        ok().json().contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(service.findAll().also { it.onEach { log.debug("Found Movies: $it") } })

    private suspend fun processRequest(
        request: ServerRequest,
        processingFunction: KSuspendFunction1<MovieDto, MovieDto>
    ) = request.awaitBodyOrNull(MovieDto::class)!!
        .let { processingFunction.invoke(it).also { log.debug("Processed Movie: $it") } }
        .let { ok().json().bodyValueAndAwait(it) }


    fun ServerRequest.getMovieId(): UUID = UUID.fromString(pathVariable(ID))
    fun ServerRequest.getTitle(): String = queryParam(TITLE).orElse(null)

    companion object {
        const val ID = "movie_id"
        const val TITLE = "title"
    }
}