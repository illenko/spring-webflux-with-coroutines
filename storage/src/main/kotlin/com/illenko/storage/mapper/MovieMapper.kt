package com.illenko.storage.mapper

import com.illenko.storage.domain.dto.MovieDto
import com.illenko.storage.domain.entity.Movie
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class MovieMapper {
    @Mapping(target = "source", constant = "Internal DB")
    abstract fun toDto(movie: Movie): MovieDto

    abstract fun fromDto(movie: MovieDto): Movie
}