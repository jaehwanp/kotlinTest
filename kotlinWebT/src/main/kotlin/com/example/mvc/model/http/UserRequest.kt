package com.example.mvc.model.http

import com.example.mvc.annotaion.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

//import com.fasterxml.jackson.annotation.JsonProperty
//import com.fasterxml.jackson.databind.PropertyNamingStrategy
//import com.fasterxml.jackson.databind.annotation.JsonNaming

//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(

    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name:String?=null,

    @field:PositiveOrZero // 양수 + 0 검증
    var age:Int?=null,

    @field:Email
    var email:String?=null,

    @field:NotBlank // 공백 검증
    var address:String?=null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber:String?=null,

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다")
    var createdAt:String?=null //yyyy-MM-dd HH:mm:ss
) {

//    @AssertTrue(message = "생성일자 패턴 : yyyy-MM-dd HH:mm:ss")
//    private fun isValidCreatedAt():Boolean {
//        return try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        }catch (e:Exception) {
//            false
//        }
//    }

}