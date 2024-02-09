package com.illenko.finder.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie(
    @JsonProperty("Title") val title: String,
    @JsonProperty("Year") val year: String,
    @JsonProperty("Genre") val genre: String = "",
    @JsonProperty("Plot") val description: String = "",
    @JsonProperty("Country") val country: String = ""
)