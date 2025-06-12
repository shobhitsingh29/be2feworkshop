package de.phgieschen.fe2be.snapshot.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthenticationInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(AuthenticationInterceptor::class.java)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        logger.info("Applying authentication interceptor")
        return if(request.getHeader(HttpHeaders.AUTHORIZATION).isNullOrEmpty()) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            false
        } else true
    }
}
