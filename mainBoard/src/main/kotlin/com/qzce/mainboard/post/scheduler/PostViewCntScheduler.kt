package com.qzce.mainboard.post.scheduler

import com.qzce.mainboard.post.service.PostRedisService
import com.qzce.mainboard.post.service.PostService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PostViewCntScheduler(
    private val redisService: PostRedisService,
    private val postService: PostService
){

    @Scheduled(fixedRate = 30000) // 30초마다 실행
    fun syncViewCntToDB() {
        val posts = redisService.getAllPostViewCnt()

        posts.forEach{ (id, viewCnt) ->
            postService.incrementViewCnt(id, viewCnt)
            redisService.resetViewCnt(id)
        }
    }

}