package com.illenko.finder.service

import com.illenko.finder.client.MovieDbClient
import com.illenko.finder.mapper.MovieMapper
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val movieDbClient: MovieDbClient, private val movieMapper: MovieMapper
) {

    suspend fun search(title: String) = movieDbClient.getByTitle(title).awaitSingleOrNull()?.let { movieMapper.toResponse(it) }
}