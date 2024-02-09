package com.illenko.finder.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "finder.timeout")
class FeignClientTimeoutProperties(val connection: Long, val read: Long, val write: Long)