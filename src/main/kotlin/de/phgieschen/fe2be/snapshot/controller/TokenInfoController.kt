package de.phgieschen.fe2be.snapshot.controller

import de.phgieschen.fe2be.snapshot.dto.UserInfoDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenInfoController {
    @GetMapping("/userinfo")
    fun getTokenInfo(
        @RequestParam token: String,
    ): ResponseEntity<UserInfoDTO> {
        val userInfo = when(token) {
            "ZALANDO" -> UserInfoDTO.zalando()
            "SUPPLIER" -> UserInfoDTO.supplier()
            else -> throw IllegalArgumentException("Token invalid")
        }
        return ResponseEntity.ok(userInfo)
    }
}
