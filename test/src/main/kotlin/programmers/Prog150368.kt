package programmers

import java.util.*

// 실패
class Prog150368 {

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        val discountRates = arrayOf(10, 20, 30, 40)
        val n = emoticons.size
        var maxJoin = 0
        var maxSales = 0

        val queue: Queue<List<Int>> = LinkedList()
        queue.add(listOf())

         while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.size == n) {
                val (join, sales) = calc(users, emoticons, current)
                if (join > maxJoin || (join == maxJoin && sales > maxSales)) {
                    maxJoin = join
                    maxSales = sales
                }
            } else {
                for (rate in discountRates) {
                    queue.add(current + rate)
                }
            }
        }

        return intArrayOf(maxJoin, maxSales)
    }

    // 할인율 조합으로 이모티콘 구매 결과 계산
    fun calc(users: Array<IntArray>, emoticons: IntArray, rates: List<Int>): Pair<Int, Int> {
        var joinCount = 0
        var totalSales = 0

        for (user in users) {
            val minRate = user[0]
            val limit = user[1]
            var cost = 0

            for (i in emoticons.indices) {
                if (rates[i] >= minRate) {
                    cost += emoticons[i] * (100 - rates[i]) / 100
                }
            }

            if (cost >= limit) {
                joinCount++
            } else {
                totalSales += cost
            }
        }

        return Pair(joinCount, totalSales)
    }
}

fun main() {
    var users = arrayOf(
        intArrayOf(40, 10000),
        intArrayOf(25, 10000)
    )
//    users = arrayOf(
//        intArrayOf(40, 2900),
//        intArrayOf(23, 10000),
//        intArrayOf(11, 5200),
//        intArrayOf(5, 5900),
//        intArrayOf(40, 3100),
//        intArrayOf(27, 9200),
//        intArrayOf(32, 690),
//    )
    var emoticons = intArrayOf(7000, 9000)
//    emoticons = intArrayOf(1300, 1500, 1600, 4900)

    println(Prog150368().solution(users, emoticons).forEach { println(it) })
}