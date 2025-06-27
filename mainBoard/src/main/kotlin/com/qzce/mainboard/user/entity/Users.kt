package com.qzce.mainboard.user.entity

import jakarta.persistence.*

@Entity
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val username: String = "",
    val password: String = ""
)