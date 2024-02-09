package com.illenko.finder.client.interceptor

import com.illenko.finder.properties.MovieDbClientProperties
import org.springframework.util.StringUtils
import org.springframework.web.util.UriComponentsBuilder
import reactivefeign.client.ReactiveHttpRequest
import reactivefeign.client.ReactiveHttpRequestInterceptor
import reactor.core.publisher.Mono
import java.nio.charset.Charset

class ApiKeyInterceptor(private val movieDbClientProperties: MovieDbClientProperties) : ReactiveHttpRequestInterceptor {

    /**  Add apiKey to query parameters */
    override fun apply(request: ReactiveHttpRequest) = Mono.just(
        ReactiveHttpRequest(
            request, UriComponentsBuilder.fromUriString(
                StringUtils.uriDecode(
                    request.uri().toString(), Charset.defaultCharset()
                )
            ).queryParam(AUTHORIZATION, movieDbClientProperties.apiKey).build().toUri()
        )
    )

    companion object {
        private const val AUTHORIZATION = "apiKey"
    }
}