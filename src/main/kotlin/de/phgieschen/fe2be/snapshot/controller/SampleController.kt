package de.phgieschen.fe2be.snapshot.controller

import de.phgieschen.fe2be.snapshot.dto.RequestDTO
import de.phgieschen.fe2be.snapshot.dto.ResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @GetMapping("/sample")
    fun getSample(): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(ResponseDTO(status = 201, message = "Sample message"))
    }

    @GetMapping("/sample/{message}")
    fun getSampleMessage(
        @PathVariable message: String
    ): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(ResponseDTO(status = 201, message = message))
    }

    @GetMapping("/sample/query/{message}")
    fun getSampleMessageQuery(
        @PathVariable message: String,
        @RequestParam param: String,
    ): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(ResponseDTO(status = 201, message = "$message - $param"))
    }

    @RequestMapping(
        value = ["/sample"],
        method = [RequestMethod.POST],
    )
    fun postBody(
        @RequestBody request: RequestDTO
    ): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(ResponseDTO(status = 201, message = request.message))
    }
}
