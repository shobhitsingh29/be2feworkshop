package de.phgieschen.fe2be.snapshot.dto

import java.time.LocalDateTime

data class PlanetsDTO(
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val created: LocalDateTime
)
