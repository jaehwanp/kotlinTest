package com.qzce.mainboard.post.repository

import com.qzce.mainboard.post.entity.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PostRepository : JpaRepository<Post, Long> {

    fun findAllByOrderByIdDesc(pageable: Pageable): List<Post>

    @Modifying
    @Query("UPDATE Post p SET p.viewCnt = p.viewCnt + :cnt WHERE p.id = :id")
    fun incrementViewCnt(id: Long, cnt: Long = 1): Int
}