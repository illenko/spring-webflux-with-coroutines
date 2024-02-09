package com.illenko.storage.kafka.listener

import com.illenko.storage.domain.SourcedBy
import com.illenko.storage.domain.dto.MovieDto
import com.illenko.storage.service.MovieService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MovieListener(private val movieService: MovieService) {

    private val log = KotlinLogging.logger {}

    @OptIn(DelicateCoroutinesApi::class)
    @KafkaListener(topics = ["movie_topic"], containerFactory = "movieKafkaFactory", groupId = "foo")
    fun movieListener(movie: MovieDto) {
        log.debug { "Received from Kafka topic movie $movie" }
        movie.sourcedBy = SourcedBy.FINDER
        GlobalScope.launch(Dispatchers.Default) {
            movieService.create(movie).also { log.debug { "Saved in internal DB from Kafka topic movie $it" } }
        }
    }
}