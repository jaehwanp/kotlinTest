package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    // 1. path variable
    // 2. request param

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name") _name : String,

        @NotNull(message = "age값 누락")
        @Min(value=20, message = "20 이상")
        @RequestParam(value = "age") _age : Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

    @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
    fun deleteMappingPath(
        @NotNull
        @Size(min = 2, max = 5, message = "5글자 넘음")
        @PathVariable(value = "name") _name: String,

        @NotNull(message = "age값 누락")
        @Min(value=20, message = "20 이상")
        @PathVariable(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

}