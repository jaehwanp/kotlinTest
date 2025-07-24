package programmers

class Prog340212 {

    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        var max = diffs[0].toLong()
        var min = diffs[0].toLong()
        for (i in 1 until diffs.size) {
            val e = diffs[i]
            if (max < e) max = e.toLong()
            if (min > e) min = e.toLong()
        }

        var answer = max
        while (min <= max) {
            val mid = (min+max)/2
            val c = search(mid, diffs, times)

            if (c <= limit) {
                answer = mid
                max = mid-1
            } else {
                min = mid+1
            }
        }

        return answer.toInt()
    }

    private fun search(mid: Long, diffs: IntArray, times: IntArray): Long {
        var result = 0L
        for (i in diffs.indices) {
            var dif = mid - diffs[i]
            if (dif >= 0) {
                result += times[i]
            } else {
                dif *= -1
                if (i == 0) {
                    result += dif * times[i]
                } else {
                    result += times[i] + dif * (times[i] + times[i - 1])
                }
            }
        }
        return result
    }


}

/*

 */
fun main() {
    var diffs = intArrayOf(1, 328, 467, 209, 54)
    var times = intArrayOf(2, 7, 1, 4, 3)
    var limit = 1723L
    Prog340212().solution(diffs, times, limit)
    diffs = intArrayOf(1,5,3)
    times = intArrayOf(2,4,7)
    limit = 30L
    Prog340212().solution(diffs, times, limit)
    diffs = intArrayOf(1,4,4,2)
    times = intArrayOf(6,3,8,2)
    limit = 59L
    Prog340212().solution(diffs, times, limit)
    diffs = intArrayOf(1,1,3)
    times = intArrayOf(1,1,3)
    limit = 50
    Prog340212().solution(diffs, times, limit)
}
