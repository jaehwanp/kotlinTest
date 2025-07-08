package com.qzce.chat.config

import com.qzce.common.jwt.JwtProvider
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    private val jwtProvider: JwtProvider,
): WebSocketMessageBrokerConfigurer {

    // 메시지 브로커 매핑
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic") // 메시지 수신 경로
        registry.setApplicationDestinationPrefixes("/app") // 메시지 송신 경로
    }

    // STOMP 엔드포인트 매핑
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        println("websocket 핸들러 /ws/chat")

        registry.addEndpoint("/ws/chat")
            .setAllowedOriginPatterns("*")

        registry.addEndpoint("/ws/chat")
            .setAllowedOriginPatterns("*")
            .withSockJS()
    }

    // 메시지 인터셉터 설정
    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        val channelInterceptor = object : ChannelInterceptor{

            override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
                val accessor = StompHeaderAccessor.wrap(message)

                if (StompCommand.CONNECT == accessor.command) {
                    val token = accessor.getFirstNativeHeader("Authorization")?.removePrefix("Bearer ")
                    if (token != null && jwtProvider.validToken(token)) {
                        val authentication = jwtProvider.getAuthentication(token)
                        accessor.user = authentication

                    } else {
                        throw IllegalArgumentException("Invalid JWT Token")
                    }
                }
                return message
            }
        }
        registration.interceptors(channelInterceptor)
    }
}