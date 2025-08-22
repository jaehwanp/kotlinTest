package programmers
/*
            intArrayOf(1,0,0,3,4,4), // 0,0 ~ 3,4
            intArrayOf(1,2,0,2,3,2),
            intArrayOf(2,1,0,3,1,2),
            intArrayOf(1,0,1,3,3,1)
 */
class Prog92344 {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer = 0
        val nb = Array(board.size+1) {IntArray(board[0].size+1)}

        for(i in skill.indices) {
            if(skill[i][0] == 1) skill[i][5] *= -1
            nb[skill[i][1]][skill[i][2]] += skill[i][5]
            nb[skill[i][1]][skill[i][4]+1] += skill[i][5] * -1
            nb[skill[i][3]+1][skill[i][2]] += skill[i][5] * -1
            nb[skill[i][3]+1][skill[i][4]+1] += skill[i][5]
        }

        // 가로
        for (i in nb.indices) {
            for (j in 1 until nb[0].size) {
                nb[i][j] += nb[i][j - 1]
            }
        }
        // 세로
        for (j in nb[0].indices) {
            for (i in 1 until nb.size) {
                nb[i][j] += nb[i - 1][j]
            }
        }

        board.mapIndexed {i, row ->
            row.mapIndexed {j, v ->
                val value = v + nb[i][j]
                if(value > 0) answer++
                value
            }.toIntArray()
        }.toTypedArray()

        return answer
    }
}

fun main() {
//    Prog92344().solution(
//        arrayOf(
//            intArrayOf(5,5,5,5,5),
//            intArrayOf(5,5,5,5,5),
//            intArrayOf(5,5,5,5,5),
//            intArrayOf(5,5,5,5,5)
//        ),
//        arrayOf(
//            intArrayOf(1,0,0,3,4,4), // 0,0 ~ 3,4
//            intArrayOf(1,2,0,2,3,2),
//            intArrayOf(2,1,0,3,1,2),
//            intArrayOf(1,0,1,3,3,1)
//        )
//    ) // 10

    Prog92344().solution(
        arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9)
        ),
        arrayOf(
            intArrayOf(1,1,1,2,2,4),
            intArrayOf(1,0,0,1,1,2),
            intArrayOf(2,2,0,2,0,100)
        )
    ) // 6

    /*
,-4,-4,-4,-4,-4, 0,
,-4,-4, 0, 0, 0, 0,
,-6,-6,-2,-2, 0, 0,
,-4,-4, 2, 2, 0, 0,
, 0, 0, 4, 4, 4, 0,
     */
}