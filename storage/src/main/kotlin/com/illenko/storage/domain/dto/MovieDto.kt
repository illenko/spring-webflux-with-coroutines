package com.illenko.storage.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.illenko.storage.domain.SourcedBy
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDto(
    var id: UUID? = null,
    var title: String = "",
    var year: Int = 0,
    var genre: String = "",
    var description: String = "",
    var country: String = "",
    var source: String = "",
    var sourcedBy: SourcedBy = SourcedBy.USER
)