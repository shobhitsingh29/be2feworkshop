package de.phgieschen.fe2be.snapshot.advise

import de.phgieschen.fe2be.snapshot.dto.ErrorDTO
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    fun processIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorDTO> {
        logger.info("Exception occurred: ${ex.message}. handling it")

        return ResponseEntity
            .status(BAD_REQUEST.value())
            .body(ErrorDTO(message = ex.message ?: "unknown", cause = "the user", code = BAD_REQUEST.value()))
    }
}
