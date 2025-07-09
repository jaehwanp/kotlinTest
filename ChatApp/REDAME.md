# 실시간 채팅 back-end

Spring STOMP 공부용으로 만들기

https://github.com/jaehwanp/kotlinTest/tree/main/ChatApp

- 📌 Root 구조

  ![](https://velog.velcdn.com/images/jaehwanp/post/31951141-ea16-4072-9502-7bc3b7f49112/image.png)

### 각 모듈별 사용처

Common - 각 모듈에서의 JWT 처리 (발급, 검증 및 관리)
Account - 회원가입, 로그인, 계정정보 관리
Chat - STOMP를 사용한 WebSocket 채팅

- 📌 흐름도

  ![](https://velog.velcdn.com/images/jaehwanp/post/479f6c43-c919-40df-8085-f1a22ba191c3/image.png)

- 📌 console

  ![](https://velog.velcdn.com/images/jaehwanp/post/3485e11b-2853-4804-86b4-ef1ebdf413ab/image.png)

## JWT

```
// JwtProvider

    fun getAccessToken(accountId: String): String {
        return Jwts.builder()
            .setSubject(accountId) // 제목
            .setExpiration(Date(System.currentTimeMillis() + accessTokenExpiration)) // 토큰만료기한
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getRefreshToken(accountId: String): String {
        return Jwts.builder()
            .setSubject(accountId)
            .setExpiration(Date(System.currentTimeMillis() + refreshTokenExpiration)) // 토큰만료기한
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun getAccountId(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token).body.subject
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
   		 ...
       return UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
    }
```

아래 `getAuthentication` 메서드에서는 소켓 연결시`WebSocketMessageBrokerConfigurer.configureClientInboundChannel.preSend`에서 메시지 인터셉터를 활용해 토큰을 확인할 때 사용한다.

## kafka

- 사이드 프로젝트에서는 kafka를 넣을 이유가없지만, 어쨌든 이런 채팅 프로그램은 다수의 유저가 이용하여 대용량의 메시지를 보내기때문에 kafka를 적용해보고싶었다.

```
// producer

    fun send(message: ChatMessageEvent) {
        val json = objectMapper.writeValueAsString(message)
        kafkaTemplate.send("chat-message", message.roomId, json)
    }

// consumer

    @KafkaListener(topics = ["chat-message"], groupId = "chat")
    fun consume(record: ConsumerRecord<String, String>) {
    ...
    // WebSocket 전송 완료
 messagingTemplate.convertAndSendToUser(event.receiverId, "/queue/messages", event)

    }
```

일단 kafka를 사용했으니 부하테스트를 진행하려고 테스트 툴을 찾아보다가 artillery를 이용하여 해보려했지만 전부 실패메시지가 떴다.

- 📌 실패 (Artillery는 STOMP를 지원하지 않는다는걸 알았다)
  ![](https://velog.velcdn.com/images/jaehwanp/post/cea1ff0f-13ef-4e4d-b985-72af544d65d8/image.png)

- 📌 임시 테스트 (chatGPT에게 부탁하여 NodeJS로 소켓 테스트만 함)
  ![](https://velog.velcdn.com/images/jaehwanp/post/8c7ad593-4f89-4d46-ac20-7bcb5be031ce/image.png)

100명의 클라이언트로 100번 메시지 보냄 -> 10000개의 메시지
02:46.691152 (시작) ~ 03:01.019484 (끝) -> 대략 15초
