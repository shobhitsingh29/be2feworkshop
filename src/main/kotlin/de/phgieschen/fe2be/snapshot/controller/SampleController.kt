package de.phgieschen.fe2be.snapshot.controller

import de.phgieschen.fe2be.snapshot.dto.RequestDTO
import de.phgieschen.fe2be.snapshot.dto.ResponseDTO
import de.phgieschen.fe2be.snapshot.dto.UserInfoDTO
import de.phgieschen.fe2be.snapshot.dto.UserType.SUPPLIER
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random
import kotlin.random.Random.Default

@RestController
class SampleController(
    @Value("\${sample.config.value}") val configValue: String,
) {

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

    @GetMapping("sample/config")
    fun getSampleConfig(): ResponseEntity<ResponseDTO> {
        return ResponseEntity.ok(ResponseDTO(status = 201, message = configValue))
    }

    @PostMapping("/sample/exception")
    fun throwSomething(): ResponseEntity<ResponseDTO> {
        val random = Random.nextInt(from = 0, until = 9)
        when(random) {
            in 0..3 -> throw IllegalArgumentException("something is wrong here")
            in 4..8 -> throw IllegalStateException("something is super wrong here")
            else -> return ResponseEntity.ok(ResponseDTO(status = 201, message = "Lucky you"))
        }
    }

    @GetMapping("/sample/property")
    fun getSampleWithProperty(
        @RequestAttribute("userInfo") userInfoDTO: UserInfoDTO
    ): ResponseEntity<ResponseDTO> {
        if (userInfoDTO.userType == SUPPLIER) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build()
        }
        return ResponseEntity.ok(ResponseDTO(status = 201, message = "$userInfoDTO"))
    }
}
