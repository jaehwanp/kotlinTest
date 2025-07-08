package com.qzce.account.service

import com.qzce.common.jwt.JwtProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtAuthService(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService
) {
    fun getAuthentication(token: String): Authentication {
        val username = jwtProvider.getAccountId(token)
        val userDetails = userDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}