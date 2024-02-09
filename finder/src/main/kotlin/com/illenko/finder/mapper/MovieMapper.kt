package com.illenko.finder.mapper

import com.illenko.finder.api.response.MovieResponse
import com.illenko.finder.domain.Movie
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
    @Mapping(target = "source", constant = "External API")
    abstract fun toResponse(movie: Movie): MovieResponse
}