package com.illenko.finder.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieResponse(
    var title: String = "",
    var year: Int = 0,
    var genre: String = "",
    var description: String = "",
    var country: String = "",
    var source: String = ""
)