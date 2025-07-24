package programmers

class Prog92342 {
    fun solution(n: Int, info: IntArray): IntArray {
        var a = expectByOneShot(info)
        var m = IntArray(11)
        var num = n // 총 발수
        val iterator = a.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            num -= item.n
            if (num < 0) {
                num += item.n
                break
            }
            m[10 - item.q] = item.n
            iterator.remove()
        }
        if(num == n) {
            return intArrayOf(-1)
        }

        a = a.filter { it.n <= num }.toMutableList()

        if(a.size == 0) {
            m[10] = num
        } else {
            m[10-a[0].q] = num
        }

        // 무승부
        if(draw(info, m)) {
            return intArrayOf(-1)
        }
        return m
    }

    private fun draw(info: IntArray, m: IntArray): Boolean {
        var apeach = 0
        var rion = 0
        var j = 10
        for(i in 0..10) {
            if(info[i] >= m[i]) {
                if(info[i] != 0) {
                    apeach += j
                }
            } else {
                rion += j
            }
            j--
        }

        return apeach == rion  // 무승부면 true
    }

    private fun expectByOneShot(info: IntArray): MutableList<Yk> {
        var c = 10.0
        var a = mutableListOf<Yk>()
        for(i in info) {
            if(i != 0) {
                a.add(Yk(i+1 , (c*2 / (i + 1)).toFloat(), c.toInt()))
            } else {
                a.add(Yk(i+1 , (c / (i + 1)).toFloat(), c.toInt()))
            }
            c--
        }
        return a.sortedWith(
            compareByDescending<Yk> { it.z }
                .thenByDescending { it.n }
        ).toMutableList()
    }
}

data class Yk(
    val n: Int,
    val z: Float,
    val q: Int
)

fun main() {
    val n = 3
//    var info = intArrayOf(2,1,1,1,0,0,0,0,0,0,0) // 5
//    val info = intArrayOf(1,0,0,0,0,0,0,0,0,0,0) // 1
//    var info = intArrayOf(0,0,1,2,0,1,1,1,1,1,1) // 9
//    var info = intArrayOf(0,0,0,0,0,0,0,0,3,4,3) // 10
    var info = intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0) // 3
    /*
    10*
       1    1    1    1    1    1    1    1    4    5    4
      10    9    8    7    6    5    4    3    2    1    0
    [10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 1.0, 0.4, 0.0]
                                               3    4    3
       1    1    1    1    1    1    1    1              2

       1    1    2    3    1    2    2    2    2    2    2
      10    9    8    7    6    5    4    3    2    1    0
    [10.0, 9.0, 8.0, 4.6, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0, 0.0]
                 1    2         1    1    1    1    1    1
       1    1    2         1    2    2




     3     2    2    2    1    1    1    1    1    1    1
     10    9    8    7    6    5    4    3    2    1    0
    [3.3, 4.5, 4.0, 3.5, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0, 0.0]
      2    1    1    1   --------------------------------
           2    2         1



      3    2
      3         2
      3              2
      0    2    2         1
           2              1    1    1
           2              1    1    1
           2    2    0    1
           2              1    1


     */
    // [0,2,2,0,1,0,0,0,0,0,0]

    Prog92342().solution(n, info)
}

/*
어피치 점수 구하기 총점.
5
10 5      00001111111     8 7 6   10 10 9 9 5

발당 기댓값.

 */