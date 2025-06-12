package de.phgieschen.fe2be.snapshot.config

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()
        .setDefaultPropertyInclusion(NON_NULL)
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(FAIL_ON_NULL_FOR_PRIMITIVES, true)
        .configure(USE_BIG_DECIMAL_FOR_FLOATS, true)
        .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
        .disable(WRITE_DATES_AS_TIMESTAMPS)
        .registerModules(JavaTimeModule(), kotlinModule())
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
}

