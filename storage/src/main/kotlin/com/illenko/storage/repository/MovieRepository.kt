package com.illenko.storage.repository

import com.illenko.storage.domain.entity.Movie
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MovieRepository : CoroutineCrudRepository<Movie, UUID> {
    suspend fun findByTitleEquals(title: String): Movie?
}