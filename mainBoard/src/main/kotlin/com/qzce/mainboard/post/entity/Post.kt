package com.qzce.mainboard.post.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name= "posts")
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "제목은 필수")
    var title: String = "",

    @field:NotBlank(message = "내용은 필수")
    var content: String = "",

    var author: String = "",

    var viewCnt: Long = 0
)