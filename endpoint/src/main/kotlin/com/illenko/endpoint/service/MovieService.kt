package com.illenko.endpoint.service

import com.illenko.endpoint.api.request.MovieRequest
import com.illenko.endpoint.api.response.MovieResponse
import com.illenko.endpoint.client.FinderClient
import com.illenko.endpoint.client.StorageClient
import com.illenko.endpoint.kafka.producer.MovieProducer
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*


@Service
class MovieService(
    private val finderClient: FinderClient,
    private val storageClient: StorageClient,
    private val movieProducer: MovieProducer
) {

    val log = KotlinLogging.logger {}

    suspend fun create(request: MovieRequest) = storageClient.post(request).awaitSingle()

    suspend fun update(request: MovieRequest) = storageClient.put(request).awaitSingle()

    suspend fun findById(id: UUID) =
        storageClient.findById(id).awaitSingleOrNull()?.also { log.debug { "Found Movie by Id $id: $it" } }

    suspend fun findAll() = storageClient.findAll().asFlow().onEach { log.debug { "Found Movies: $it" } }

    suspend fun search(title: String): MovieResponse? =
        storageClient.search(title).awaitSingleOrNull()
            ?.also { log.debug { "Found Movie with title $title in Storage Service: $it" } }
            ?: finderClient.search(title).awaitSingleOrNull()
                ?.also { log.debug { "Found Movie with title $title in Storage Service: $it" } }
                ?.also {
                    log.debug { "Sending Movie with title $title in Kafka Topic: $it" }
                    movieProducer.send(it)
                }
}