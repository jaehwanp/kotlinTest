package programmers

class Prog388352 {

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        var answer: Int = 0
        answer = combinations(q, ans, n)
        return answer
    }

    // 0 ~ n 조합 배열
    private fun combinations(q: Array<IntArray>, ans: IntArray, n: Int): Int {
        var cnt = 0
        var a: IntArray
        var same = false
        val combination = mutableListOf<Int>()

        fun combine(start: Int) {
            if (combination.size == 5) {
                a = combination.toIntArray()
                same = compare(q,ans,a)
                if(same) cnt++
                return
            }

            for (i in start..n) {
                combination.add(i)
                combine(i + 1)
                combination.removeAt(combination.size - 1)
            }
        }

        combine(1)
        return cnt
    }

    private fun compare(q: Array<IntArray>, ans: IntArray, a: IntArray): Boolean {
        for(i in q.indices) {
            var sameCnt = q[i].count {it in a}
            if(ans[i] != sameCnt) {
                return false
            }
        }
        return true
    }


}

fun main() {
    val n = 15
    val q = arrayOf(
        intArrayOf(2, 3, 9, 12, 13),
        intArrayOf(1, 4, 6, 7, 9),
        intArrayOf(1, 2, 8, 10, 12),
        intArrayOf(6, 7, 11, 13, 15),
        intArrayOf(1, 4, 10, 11, 14)
    )
    val ans: IntArray = intArrayOf(2,1,3,0,1)

    var answer = Prog388352().solution(n,q,ans)
    println(answer)
}

