package com.illenko.finder.config

import com.illenko.finder.client.MovieDbClient
import com.illenko.finder.client.interceptor.ApiKeyInterceptor
import com.illenko.finder.properties.FeignClientTimeoutProperties
import com.illenko.finder.properties.MovieDbClientProperties
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import reactivefeign.ReactiveContract
import reactivefeign.webclient.WebReactiveFeign
import reactivefeign.webclient.WebReactiveOptions

@Configuration
class WebClientConfig(
    private val feignClientTimeoutProperties: FeignClientTimeoutProperties,
    private val movieDbClientProperties: MovieDbClientProperties
) {

    @Bean
    fun movieDbClient(): MovieDbClient =
        WebReactiveFeign.builder<MovieDbClient>(WebClient.builder()).addRequestInterceptor(apiKeyInterceptor())
            .contract(ReactiveContract(SpringMvcContract())).options(getWebReactiveOptions())
            .target(MovieDbClient::class.java, movieDbClientProperties.url)

    @Bean
    fun apiKeyInterceptor() = ApiKeyInterceptor(movieDbClientProperties)

    private fun getWebReactiveOptions() =
        WebReactiveOptions.Builder().setReadTimeoutMillis(feignClientTimeoutProperties.read)
            .setWriteTimeoutMillis(feignClientTimeoutProperties.write).setFollowRedirects(false)
            .setConnectTimeoutMillis(feignClientTimeoutProperties.connection).build()

}