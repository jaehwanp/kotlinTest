package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // get 4xx
    // GET http://localhost:8080/api/response
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {   // Int? : ?는 비어있어도 됨

        return age?.let {

            if(it < 20) {
                return ResponseEntity.status(400).body("age는 20보다 커야함")
            }

            return ResponseEntity.ok("OK")

        }?: kotlin.run {
            return ResponseEntity.status(400).body("age 누락")
        }

        // age == null -> 400
//        if (age == null) {
//            return ResponseEntity.status(400).body("age 누락")
//        }

//        if (age < 20) {
//            return ResponseEntity.status(400).body("age는 20보다 커야함")
//        }

        // age > 10 -> 400
//        return ResponseEntity.ok("OK")

    }

    // post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터 부재
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id:Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }

}