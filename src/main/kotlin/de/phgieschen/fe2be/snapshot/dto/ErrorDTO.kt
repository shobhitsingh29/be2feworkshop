package de.phgieschen.fe2be.snapshot.dto

data class ErrorDTO(
    val message: String,
    val cause: String,
    val code: Int
)
