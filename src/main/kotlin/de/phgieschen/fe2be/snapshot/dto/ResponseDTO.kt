package de.phgieschen.fe2be.snapshot.dto

import java.time.LocalDateTime

data class ResponseDTO(
    val status: Int,
    val message: String,
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
)
