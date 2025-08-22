package programmers

import java.util.*
import kotlin.math.pow

class Prog150367 {
    fun solution(numbers: LongArray): IntArray {
        var answer = IntArray(numbers.size)
        for((index, n) in numbers.withIndex()) {
            val bool = isBinaryTree(toBinaryForm(n, toBinaryNum(n)))
            if(bool) answer[index] = 1
        }
        return answer
    }

    // to 2진수
    private fun toBinaryNum(n: Long): String {
        if(n == 0L) return "0"
        if(n == 1L) return "1"
        return toBinaryNum(n/2) + (n%2)
    }

    // to 이진트리 (정규식 :2^k - 1)
    private fun toBinaryForm(n: Long, s: String): String {
        var d = 0.0
        for(i in 0..10) {
            d = 2.0.pow(i.toDouble())
            if(s.length < d) {
                d -= 1
                break
            }
        }
        return "0".repeat(d.toInt() - s.length) + s
    }

    // 이진트리인지 체크
    private fun isBinaryTree(s: String): Boolean {
        val q: Queue<String> = LinkedList()
        q.offer(s)

        while(q.isNotEmpty()){
            val nq = q.poll()
            if(nq.length <= 1) break

            val startIndex = nq.length/2
            val start = nq[startIndex]

            val ls = nq.substring(0, startIndex)
            val rs = nq.substring(startIndex+1)

            if(start == '0') {
                if (nq.indexOf('1') != -1) return false
            } else {
                q.offer(ls)
                q.offer(rs)
            }
        }
        return true
    }
}

fun main() {
//    Prog150367().solution(longArrayOf(7,42,4)) // [1,1,0]
//    Prog150367().solution(longArrayOf(63,111,95)) // [1,1,0]
    Prog150367().solution(longArrayOf(423)) // [1,1,0]
}