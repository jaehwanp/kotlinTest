package programmers

import java.util.*

/*
 우선순위 큐 사용.
 */
class Prog142085 {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val heap = PriorityQueue<Int>(compareByDescending { it })
        var a = k
        var sum = 0

        for (i in enemy.indices) {
            sum += enemy[i]
            heap.offer(enemy[i])

            if (sum > n) {
                if (k > 0) {
                    sum -= heap.poll()
                    a--
                } else {
                    return i
                }
            }
        }
        return enemy.size
    }
}

fun main() {
//    Prog142085().solution(7, 3, intArrayOf(4,2,4,5,3,3,1))
//    Prog142085().solution(2, 4, intArrayOf(3,3,3,3))
//    Prog142085().solution(5, 5, intArrayOf(5,5,5,5,5,5,5))
//    Prog142085().solution(5, 4, intArrayOf(1,1,1,1,1,1,1,99,99,99,99))
}

/*
    var max = 0
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        if(k >= enemy.size) {
            return enemy.size
        }
        dfs(n, k, enemy, 0)
        return max
    }

    private fun dfs(n: Int, k: Int, enemy: IntArray, i: Int) {
        if(i==enemy.size) {
            max = maxOf(max, i)
            return
        }

        // 막음
        if(n-enemy[i] >= 0) {
            dfs(n-enemy[i], k, enemy, i+1)
        }

        // 안막음
        if(k>0) {
            dfs(n, k-1, enemy, i+1)
        }

        // 무적권 없음
        if(n-enemy[i] < 0 && k == 0) {
            max = maxOf(max, i)
        }
    }
 */
