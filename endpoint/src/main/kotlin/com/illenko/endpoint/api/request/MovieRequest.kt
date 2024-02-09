package com.illenko.endpoint.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieRequest(
    var title: String = "",
    var year: Int = 0,
    var genre: String = "",
    var description: String = "",
    var country: String = "",
    var source: String = ""
)