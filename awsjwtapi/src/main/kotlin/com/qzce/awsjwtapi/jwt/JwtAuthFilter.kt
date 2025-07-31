package com.qzce.awsjwtapi.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtProvider: JwtProvider
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearer = request.getHeader("Authorization")
        val token = if(bearer?.startsWith("Bearer ") == true) bearer.substring(7) else null

        if (token != null && jwtProvider.validateToken(token)) {
            val username = jwtProvider.getAccountId(token)
            val auth = UsernamePasswordAuthenticationToken(username, null, listOf())
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(request, response)
    }
}