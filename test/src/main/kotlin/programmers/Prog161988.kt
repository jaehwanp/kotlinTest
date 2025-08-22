package programmers

class Prog161988 {
    fun solution(sequence: IntArray): Long {
        val odd = divideArray(sequence, 0)
        val even = divideArray(sequence, 1)

        val o = getMaxSumFromArray(odd)
        val e = getMaxSumFromArray(even)

        return if(o > e) o else e
    }

    private fun getMaxSumFromArray(sequence: IntArray): Long {
        var maxSum = 0L
        var nowSum = 0L

        for((i, v) in sequence.withIndex()) {
            nowSum += v
            if(nowSum > maxSum) {
                maxSum = nowSum
            }
            if(nowSum <= 0 && i < sequence.size-1) {
                nowSum = 0
            }
        }
        return maxSum
    }

    private fun divideArray(sequence: IntArray, g: Int): IntArray {
        val arr = IntArray(sequence.size)
        for(i in arr.indices) {
            arr[i] = sequence[i]
            if(i%2 == g) {
                arr[i] *= -1
            }
        }
        return arr
    }
}

fun main() {
//    Prog161988().solution(intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)) // 10
//    Prog161988().solution(intArrayOf(1)) // 1
//    Prog161988().solution(intArrayOf(1,2,3,4,5,6,7,8,9,-10)) // 19
//    Prog161988().solution(intArrayOf(1,-2,3,-4,-30)) // 26
//    Prog161988().solution(intArrayOf(1,8,3,1,1,30,1,1,1,1,1,1,50,20)) // 50
    Prog161988().solution(intArrayOf(-100000,-100000,-100000,-100000,-100000)) //

    // 시작 끝 max값 현재sum
    //  2  2   3    50

    // -1 8 -3 1 -1 30 -1 1 -1 1 -1 1 -30 1
    //    8 -3 1 -1 30 -1 1 -1 1 -1 1 -30 1

    // 1 -8 3 -1 1 -30 1 -1 1 -1 1 -1 50 -20
    // 1 -8 3 -1 1 -30 1 -1 1 -1 1 -1 50

}