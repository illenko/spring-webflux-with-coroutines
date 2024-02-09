package com.illenko.finder.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "finder.movie-db-client")
class MovieDbClientProperties(val url: String, val apiKey: String)