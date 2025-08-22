package com.qzce.awsjwtapi.auth.social.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoUserResponse (
    val id: Long,
//    @JsonProperty("kakao_account")
//    val kakaoAccount: KakaoAccount
)

/*
이런 형식으로 날라옴
{
    "id": xxxxxxxxxxxx,
    "connected_at": "20xx-xx-xxTxx:xx:xxZ",
    "properties": {
        "nickname": "xxx"
    },
    "kakao_account": {
        "profile_nickname_needs_agreement": false,
        "profile": {
            "nickname": "xxx",
            "is_default_nickname": false
        }
    }
}
 */