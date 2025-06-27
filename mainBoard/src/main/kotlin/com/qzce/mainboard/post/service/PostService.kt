package com.qzce.mainboard.post.service

import com.qzce.mainboard.post.entity.Post
import com.qzce.mainboard.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(private val postRepository: PostRepository) {
    fun create(post: Post): Post = postRepository.save(post)
    fun findAll(): List<Post> = postRepository.findAll()
    fun findById(id: Long): Post = postRepository.findById(id).orElseThrow()
    fun delete(id: Long) = postRepository.deleteById(id)

    @Transactional
    fun incrementViewCnt(id: Long, cnt: Long) = postRepository.incrementViewCnt(id, cnt)
}