package com.qzce.awsjwtapi.auth.social

import com.qzce.awsjwtapi.account.Account
import com.qzce.awsjwtapi.auth.AuthRepository
import com.qzce.awsjwtapi.auth.dto.TokenResponse
import com.qzce.awsjwtapi.auth.social.dto.KakaoTokenResponse
import com.qzce.awsjwtapi.auth.social.dto.KakaoUser
import com.qzce.awsjwtapi.auth.social.dto.KakaoUserResponse
import com.qzce.awsjwtapi.jwt.JwtProvider
import com.qzce.awsjwtapi.jwt.JwtRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class KakaoAuthService(
    private val jwtProvider: JwtProvider,
    private val authRepository: AuthRepository,
    private val jwtRepository: JwtRepository,
    @Value("\${oauth.kakao.clientId}") private val kakaoClientId: String,
    @Value("\${oauth.kakao.redirectUri}") private val kakaoRedirectUri: String,
    @Value("\${oauth.kakao.clientSecret}") private val kakaoClientSecret: String,
) {
    private val restTemplate = RestTemplate()

    fun getKakaoLoginUrl(): String {
        return "https://kauth.kakao.com/oauth/authorize?" +
                "client_id=$kakaoClientId&" +
                "redirect_uri=$kakaoRedirectUri&" +
                "client_secret=$kakaoClientSecret&" +
                "response_type=code"
    }

    fun loginWithKakao(code: String): TokenResponse {
        val accessToken = getAccessTokenFromKakao(code)
        val kakaoUser = getUserInfoFromKakao(accessToken)

        val account = authRepository.findByKakaoId(kakaoUser.id)
            ?: authRepository.save(Account(kakaoId = kakaoUser.id))

        val access = jwtProvider.createAccessToken(account.accountId)
        val refresh = jwtProvider.createRefreshToken(account.accountId)
        jwtRepository.save(account.accountId, refresh)

        return TokenResponse(accessToken = access, refreshToken = refresh)
    }

    private fun getAccessTokenFromKakao(code: String): String {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }

        val body = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", kakaoClientId)
            add("redirect_uri", kakaoRedirectUri)
            add("code", code)
        }

        val request = HttpEntity(body, headers)

        val response = restTemplate.postForEntity(
            "https://kauth.kakao.com/oauth/token",
            request,
            KakaoTokenResponse::class.java
        )

        return response.body?.accessToken ?: throw IllegalStateException("No access token from Kakao")
    }

    private fun getUserInfoFromKakao(accessToken: String): KakaoUser {
        val headers = HttpHeaders().apply {
            setBearerAuth(accessToken)
        }

        val request = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            request,
            KakaoUserResponse::class.java
        )

        return KakaoUser(
            id = response.body?.id ?: throw IllegalStateException("No user id"),
        )
//            email = response.body?.kakaoAccount?.email ?: "unknown@kakao.com"
            //nickname = response.body?.kakaoAccount?.profile?.nickname ?: "kakao_user"
    }
}