package com.illenko.endpoint.kafka.producer

import com.illenko.endpoint.api.response.MovieResponse
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class MovieProducer(val template: KafkaTemplate<String, MovieResponse>) {

    fun send(movie: MovieResponse) = template.send(MOVIE_TOPIC, movie)

    companion object {
        const val MOVIE_TOPIC = "movie_topic";
    }
}