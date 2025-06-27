package com.qzce.mainboard.post.response

import com.qzce.mainboard.post.entity.Post

data class PostResponse(
    val message: String,
    val post: Post
)