package programmers

/*
[2,2] : 4
  5     5     5     3
[1,4],[3,2],[3,2],[2,1],[5,3]

100, 2
2, 100


50, 1

 */
class Prog152995 {
    fun solution(scores: Array<IntArray>): Int {
        val w1 = scores[0][0]
        val w2 = scores[0][1]
        val ws = w1+w2

        val ns = scores.filter { it.sum() > ws } // 완호보다 합이 큰 사람
        if(ns.any{it[0]>w1 && it[1]>w2}) return -1 // 완호보다 두 점수 모두 큰 사람
        // 두 점수 모두 적은사람
        val f = ns.filter { a ->
             ns.none { b ->
                b[0]>a[0] && b[1]>a[1]
            }
        }

        println(f.size+1)
        return f.size+1
    }
}

fun main() {
    Prog152995().solution(
        arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
        )
    ) // 4
}