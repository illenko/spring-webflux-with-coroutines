package com.illenko.storage.service

import com.illenko.storage.domain.dto.MovieDto
import com.illenko.storage.mapper.MovieMapper
import com.illenko.storage.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MovieService(private val repository: MovieRepository, private val mapper: MovieMapper) {

    suspend fun create(dto: MovieDto): MovieDto = dto.run {
        this.id = null
        update(dto)
    }

    suspend fun update(dto: MovieDto): MovieDto = mapper.fromDto(dto).let { repository.save(it) }.let(mapper::toDto)

    suspend fun findById(id: UUID): MovieDto? = repository.findById(id)?.let(mapper::toDto)

    suspend fun search(title: String): MovieDto? = repository.findByTitleEquals(title)?.let(mapper::toDto)

    suspend fun findAll(): Flow<MovieDto> = repository.findAll().mapNotNull { mapper.toDto(it) }
}