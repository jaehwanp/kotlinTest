package com.qzce.common.jwt

interface RefreshTokenRepository {
    fun save(id: String, refreshToken: String)
    fun get(id: String): String?
    fun delete(id: String)
}