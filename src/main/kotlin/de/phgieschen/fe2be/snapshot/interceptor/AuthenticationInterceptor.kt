package de.phgieschen.fe2be.snapshot.interceptor

import de.phgieschen.fe2be.snapshot.dto.UserInfoDTO
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthenticationInterceptor(
    val restTemplate: RestTemplate,
) : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(AuthenticationInterceptor::class.java)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        logger.info("Applying authentication interceptor")
        if(request.getHeader(HttpHeaders.AUTHORIZATION).isNullOrEmpty()) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
             return false
        }

        if(request.requestURL.contains("userinfo")) {
            return true
        }
        val authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION)

        val requestHeaders = HttpHeaders()
        requestHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        requestHeaders.set(HttpHeaders.AUTHORIZATION, authHeaderValue)
        val httpEntity = HttpEntity(null, requestHeaders)
        val result = restTemplate.exchange(
            "http://localhost:8080/userinfo?token=$authHeaderValue",
            HttpMethod.GET,
            httpEntity,
            UserInfoDTO::class.java
        ).body!!

        request.setAttribute("userInfo", result)
        return true
    }
}
