package programmers

import kotlin.math.*

/*
y^2 = r^2 - x^2

 */
class Prog181187 {

    fun solution1(r1: Int, r2: Int): Long {
        var answer: Long = 0
        var max: Long
        var min: Long

        for(x in 1..r2) {
            max = floor(sqrt(r2*r2*1.0 - x*x*1.0)).toLong()
            min = ceil(sqrt(r1*r1*1.0 - x*x*1.0)).toLong()

            answer += max-min+1
        }
        println(answer*4)
        return answer*4
    }

}

fun main() {
    var r1 = 2
    var r2 = 3
    // 20
    Prog181187().solution1(r1,r2)
}