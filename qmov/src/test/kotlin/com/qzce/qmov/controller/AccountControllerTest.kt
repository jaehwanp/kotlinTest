package com.qzce.qmov.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.qzce.qmov.entity.Account
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    private val path = "/account"

    @Test
    fun createTest() {
        val account = Account().apply {
            this.name = "john"
            this.password = "john1234"
            this.createdAt = LocalDateTime.now()
        }

        val json = jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(account)

        mockMvc.perform(
            post("$path/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk)
         .andExpect(jsonPath("\$.name").value("john"))
         .andExpect(jsonPath("\$.password").value("john1234"))
         .andDo(print())
    }

    @Test
    fun deleteAccountTest() {
        // createAccount
        val account = Account().apply {
            this.name = "john"
            this.password = "john1234"
            this.createdAt = LocalDateTime.now()
        }



    }

}