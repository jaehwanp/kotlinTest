package programmers

import kotlin.math.sqrt

class Prog92335 {

    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val s = n.toString(k)
        var a = s.split("0").filter { s: String -> s.isNotEmpty() && s != "1" }
        answer = a.count { primeNum(it.toLong()) }
        return answer
    }

    private fun primeNum(n: Long): Boolean {
        if(n == 2L) return true
        for (i in 3 .. Math.sqrt(n.toDouble()).toLong() step 2) {
            if(n % i == 0L) {
                return false
            }
        }
        return true
    }

}

fun main() {
         // 1000000
    // 1111111111111
    // 1111111111111
    var n = 524287
    var k = 2
    Prog92335().solution(n, k)
}