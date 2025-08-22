package programmers

import kotlin.math.sin

class Prog131129 {
    fun solution(target: Int): IntArray {
        val ms = makeScore() // 한 다트로 가능한 점수
        val m = target/60 // 60점 갯수
        val n = target%60 // 나머지 점수
        var diffM = -1
        var maxN = 0 to 0
        var singleBullSum = 0

        if (n == 0) {
            println("$m 0")
            return intArrayOf(m, 0)
        // 60 -> 50 최대로 만들기
        } else if(target > 60) {
            for (i in 0..m) {
                val np = n + i * 10
                if (np > 60) break
                diffM++
                val tf = findScore(ms, np)
                if (tf != 0) maxN = tf to diffM
            }
        } else {
            val tf = findScore(ms, n)
            return if(tf != 0) {
                        if(isSingleAndBull(n)) singleBullSum++
                        println("1 $singleBullSum")
                        intArrayOf(1,singleBullSum)
                    } else {
                        val result = divideRemain(ms, m, n)
                        println("2 $result")
                        intArrayOf(2,result)
                    }
        }

        return if(maxN.first == 0) {
                    val result = divideRemain(ms, m, n)
                    println("${m+2} $result")
                    intArrayOf(m+2, result)
                } else {
                    if(isSingleAndBull(maxN.first)) singleBullSum++
                    println("${m+1} ${singleBullSum + maxN.second}")
                    intArrayOf(m+1, singleBullSum + maxN.second)
                }
    }

    private fun divideRemain(ms: MutableList<Int>, m: Int, n: Int): Int {
        val mt = mutableListOf<Triple<Int,Int,Int>>()
        for(s in ms) {
            var singleBullSum = 0
            val diff = n-s
            val tf = findScore(ms, diff)
            if(tf != 0) {
                if(isSingleAndBull(s)) singleBullSum++
                if(isSingleAndBull(diff)) singleBullSum++
                mt.add(Triple(s, diff, singleBullSum))
            }
        }
        mt.sortByDescending { it.third }
        val mf = mt.first()
        val lt = mt.filter { it.third == mf.third }

        for(i in lt) {
            var singleBullSum = 0
            for(j in 0..m) {
                if(isSingleAndBull(i.first + j*10)) singleBullSum++
                if(isSingleAndBull(i.second + (m-j)*10)) singleBullSum++
                mt.add(Triple(0,0, singleBullSum))
            }
        }

        mt.sortByDescending { it.third }
        return mt.first().third
    }

    private fun isSingleAndBull(t: Int): Boolean {
        return t<=20 || t==50
    }

    private fun findScore(ms: MutableList<Int>, t: Int): Int {
        return ms.find { it == t } ?: 0
    }

    private fun makeScore(): MutableList<Int> {
        val ms = mutableSetOf<Int>()
        for(i in 1..20) {
            ms.add(i)
            ms.add(i*2)
            ms.add(i*3)
        }
        ms.add(50)
        return ms.sorted().toMutableList()
    }

}

fun main() {
//    Prog131129().solution(120) // 2,0
//    Prog131129().solution(21) // 1,0
//    Prog131129().solution(23) // 2,2
//    Prog131129().solution(42) // 1,0
//    Prog131129().solution(43) // 2,1
//    Prog131129().solution(44) // 2,1
//    Prog131129().solution(58) // 2,2
//    Prog131129().solution(60) // 1,0
//    Prog131129().solution(71) // 2,1
//    Prog131129().solution(82) // 2,1
//    Prog131129().solution(100) // 2,2
//    Prog131129().solution(101) // 2,1
//    Prog131129().solution(102) // 2,0
//    Prog131129().solution(169) // 4,3
//    Prog131129().solution(117) // 2,0
    Prog131129().solution(120) // 2,0
    Prog131129().solution(121) // 3,2
    Prog131129().solution(100000) // 1667,2
}

/*
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 26, 27, 28, 30, 32, 33, 34, 36, 38, 39, 40, 42, 45, 48, 50, 51, 54, 57, 60]

 */