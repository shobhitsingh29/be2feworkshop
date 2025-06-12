package de.phgieschen.fe2be.snapshot.client

import de.phgieschen.fe2be.snapshot.dto.PlanetsDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SwapiClient(
    @Value("\${swapi.url}") val url: String,
    private val restTemplate: RestTemplate,
) {

    fun getPlanet(id: Int): PlanetsDTO {
        val resource = "planets/"
        val headers = HttpHeaders()
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity(null, HttpHeaders())
        val result = restTemplate.exchange(
            "$url$resource$id",
            HttpMethod.GET,
            httpEntity,
            PlanetsDTO::class.java
        )
        return result.body!!
    }
}
