package com.qzce.mainboard.post.controller

import com.qzce.mainboard.post.entity.Post
import com.qzce.mainboard.post.response.PostResponse
import com.qzce.mainboard.post.service.PostRedisService
import com.qzce.mainboard.post.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
    private val redisService: PostRedisService
) {

    @PostMapping
    fun create(@RequestBody @Valid post: Post): ResponseEntity<PostResponse> {

        val postData = postService.create(post)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(PostResponse("SUCCESS", postData))
    }

    @GetMapping
    fun getAll() = postService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PostResponse> {

        redisService.incrementPostViewCnt(id)

        val post = postService.findById(id)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(PostResponse("SUCCESS", post))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = postService.delete(id)
}
