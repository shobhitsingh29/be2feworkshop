package de.phgieschen.fe2be.snapshot.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder
            .messageConverters(MappingJackson2HttpMessageConverter(objectMapper))
            .build()
    }
}
