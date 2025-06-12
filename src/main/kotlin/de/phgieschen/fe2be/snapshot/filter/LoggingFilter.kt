package de.phgieschen.fe2be.snapshot.filter


import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LoggingFilter : Filter  {
    val logger = LoggerFactory.getLogger(this.javaClass)

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        logger.info("received $request.")

        filterChain.doFilter(request, response)

        logger.info("sending $response")
    }
}
