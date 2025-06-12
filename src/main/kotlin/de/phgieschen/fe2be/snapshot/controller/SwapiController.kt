package de.phgieschen.fe2be.snapshot.controller

import de.phgieschen.fe2be.snapshot.client.SwapiClient
import de.phgieschen.fe2be.snapshot.dto.PlanetsDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SwapiController(
    val client: SwapiClient
) {
    @GetMapping("/swapi/planets/{id}")
    fun getPlanet(
        @PathVariable("id") id: Int
    ): ResponseEntity<PlanetsDTO> {
        return ResponseEntity.ok(client.getPlanet(id))
    }
}
