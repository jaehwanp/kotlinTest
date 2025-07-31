package programmers

import java.util.LinkedList
import java.util.Queue

class Prog118667 {

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        var size = queue1.size

        val q1: Queue<Long> = queue1.map { it.toLong() }.toCollection(LinkedList())
        val q2: Queue<Long> = queue2.map { it.toLong() }.toCollection(LinkedList())

        while(q1.size > 0 && q2.size > 0) {

            val cq1 = q1.sum()
            val cq2 = q2.sum()
            if (cq1 > cq2) {
                q2.add(q1.poll())
                answer++
            } else if (cq1 < cq2) {
                q1.add(q2.poll())
                answer++
            } else {
                break
            }

            if(answer > size*4) break
        }

        if(q1.sum() != q2.sum()) answer = -1

        println(answer)

        return answer
    }
}
/*
sum 이 큰곳에서 작은곳으로 넘김.


테스트 1 〉	실패 (5.97ms, 64.3MB)
테스트 2 〉	통과 (6.26ms, 63.9MB)
테스트 3 〉	통과 (6.55ms, 64.1MB)
테스트 4 〉	통과 (6.33ms, 63.5MB)
테스트 5 〉	통과 (6.83ms, 64MB)
테스트 6 〉	통과 (8.59ms, 63.5MB)
테스트 7 〉	통과 (8.85ms, 64.5MB)
테스트 8 〉	통과 (9.16ms, 64.7MB)
테스트 9 〉	통과 (16.02ms, 62.2MB)
테스트 10 〉	통과 (29.81ms, 62.5MB)
테스트 11 〉	실패 (시간 초과)
테스트 12 〉	통과 (4874.42ms, 79.3MB)
테스트 13 〉	통과 (352.15ms, 74.6MB)
테스트 14 〉	통과 (115.92ms, 74.7MB)
테스트 15 〉	통과 (4018.86ms, 85.6MB)
테스트 16 〉	통과 (1617.71ms, 95.7MB)
테스트 17 〉	통과 (273.61ms, 94.2MB)
테스트 18 〉	통과 (2171.60ms, 127MB)
테스트 19 〉	실패 (시간 초과)
테스트 20 〉	실패 (시간 초과)
테스트 21 〉	실패 (시간 초과)
테스트 22 〉	실패 (시간 초과)
테스트 23 〉	실패 (시간 초과)
테스트 24 〉	실패 (시간 초과)
테스트 25 〉	통과 (10.01ms, 63.9MB)
테스트 26 〉	통과 (7.87ms, 64.3MB)
테스트 27 〉	통과 (7.93ms, 62.9MB)
테스트 28 〉	실패 (시간 초과)
테스트 29 〉	통과 (24.43ms, 65.3MB)

 */

fun main() {
//    val queue1 = intArrayOf(1,2,1,2)
//    val queue2 = intArrayOf(1,10,1,2)
//    val queue1 = intArrayOf(3,2,7,2)
//    val queue2 = intArrayOf(4,6,5,1)
//    val queue1 = intArrayOf(1,1)
//    val queue2 = intArrayOf(1,5)
//    val queue1 = intArrayOf(1,1,1,20,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
//    val queue2 = intArrayOf(1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
//    val queue1 = intArrayOf(10,5,1)
//    val queue2 = intArrayOf(2,2,2)
//    val queue1 = intArrayOf(1,4,1,1)
//    val queue2 = intArrayOf(1,5,1,1)
    val queue1 = intArrayOf(1,1,1,1)
    val queue2 = intArrayOf(1,1,7,1)
    Prog118667().solution(queue1, queue2)
}