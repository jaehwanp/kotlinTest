package programmers

import java.math.BigInteger

/*
1 or 2
0
1 2
12 12
12 12 12 12


111 112 121 122 211 212 221 222
 */
class Prog12914 {
    private val arr = Array(2001) { BigInteger.ZERO }
    fun solution(n: Int): Long {
        val f = fibonacci(n)
        //println(f%1234567.toBigInteger())
        return (f%1234567.toBigInteger()).toLong()
    }

    private fun fibonacci(n: Int): BigInteger {
        if(n <= 2) return n.toBigInteger()
        return if(arr[n] != BigInteger.ZERO) {
            arr[n]
        } else {
            arr[n] = fibonacci(n-2) + fibonacci(n-1)
            arr[n]
        }
    }
//    var cnt: Long = 0
//    fun solution(n: Int): Long {
//        dfs(n, "")
//        println(cnt)
//        return cnt
//    }
//
//    fun dfs(n: Int, s: String) {
//        if(n == 0) {
//            cnt++
//            println(s)
//            return
//        }
//        // 1
//        if(n-1>=0) {
//            dfs(n - 1, s+1)
//        }
//        // 2
//        if(n-2>=0) {
//            dfs(n - 2, s+2)
//        }
//    }
}

fun main() {
//    Prog12914().solution(1) // 1
//    Prog12914().solution(2) // 2
//    Prog12914().solution(3) // 3
//    Prog12914().solution(4) // 5
//    Prog12914().solution(5) // 8
//    Prog12914().solution(6) // 13
//    Prog12914().solution(7) // 21
//    Prog12914().solution(10) // 89
//    Prog12914().solution(100) // 496748
    Prog12914().solution(2000) // 496748
}