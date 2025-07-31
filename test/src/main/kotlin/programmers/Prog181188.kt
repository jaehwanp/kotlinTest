package programmers

class Prog181188 {
    fun solution(targets: Array<IntArray>): Int {
        var m = mutableListOf<IntArray>()

        targets.sortBy { it.last() }

        for(t in targets) {
            var chk = true
            for(i in 0 until m.size) {
                if(Math.max(t[0],m[i][0]) < Math.min(t[1],m[i][1])) { // 겹침
                    if(t[0] > m[i][0]) m[i][0] = t[0]
                    if(t[1] < m[i][1]) m[i][1] = t[1]
                    chk = false
                    break
                }
            }
            if(chk) m.add(t)
        }
        println(m.size)
        return m.size
    }
}
/*
4~5
11~12
1~4


4 ~ 5 ~
11 ~ 13 ~
1 ~ 4 ~
4 ~ 8 ~
10 ~ 14 ~
3 ~ 7 ~
5 ~ 12 ~
 */

fun main() {
    val targets = arrayOf(
        intArrayOf(4,5),
        intArrayOf(4,8),
        intArrayOf(10,14),
        intArrayOf(11,13),
        intArrayOf(5,12),
        intArrayOf(3,7),
        intArrayOf(1,4)
    )
//    val targets = arrayOf(
//        intArrayOf(1,2),
//        intArrayOf(1,2),
//        intArrayOf(1,2),
//        intArrayOf(1,2),
//    )

    //3
    Prog181188().solution(targets)
}