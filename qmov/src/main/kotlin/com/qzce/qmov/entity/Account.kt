package com.qzce.qmov.entity

import org.hibernate.annotations.ColumnDefault
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,

    @Column(nullable = false)
    var name:String?=null,

    @Column(nullable = false)
    var password:String?=null,

    var createdAt: LocalDateTime?=null


)