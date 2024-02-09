package com.illenko.endpoint.mapper

import com.illenko.endpoint.api.request.MovieRequest
import com.illenko.endpoint.api.response.MovieResponse
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class MovieMapper {
    abstract fun toRequest(movie: MovieResponse): MovieRequest
}