package programmers

import java.util.LinkedList

class Prog87390 {
/*
1 2 3 4 5 6 7 8 9 / 2  2  3  4  5  6  7  8  9 / 3  3  3  4  5  6  7  8  9
0 1 2 3 4 5 6 7 8   9 10 11 12 13 14 15 16 17  18 19 20 21 22 23 24 25 26

인덱스 + 1            인덱스 + 1
0*9 + 1...9         1*9 + 1...9                 2*9 + 1...9
0/9 -> 0 ` 0    1
26/9 -> 2 ` 8   9
18/9 -> 2 ` 0   3
19/9 -> 2 ` 1   3
 */
    fun solution(n: Int, left: Long, right: Long): IntArray {
        var m = mutableListOf<Int>()
        for(i in left..right) {
            var a = (i/n).toInt()
            var b = (i%n).toInt()
            m.add(Math.max(a,b) + 1)
        }
        return m.toIntArray()
    }


    fun solution2(n: Int, left: Long, right: Long): IntArray {
        var answer: IntArray = intArrayOf()
        val result = Array(n) { IntArray(n) { 0 } }
        var m = mutableListOf<Int>()
        val q = LinkedList<Pair<Int, Int>>()
        val dx = intArrayOf(0,0,1,-1)
        val dy = intArrayOf(1,-1,0,0)

        q.add(Pair(0,0))

        while(q.isNotEmpty()) {
            val (x,y) = q.poll()
            result[x][y] = if(x>y) x+1 else y+1
            for(i in 0..3) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(nx in 0 until n && ny in 0 until n && result[nx][ny] == 0) {
                    q.add(Pair(nx,ny))
                }
            }
        }

        val lx = left/n
        val ly = left%n
        val rx = right/n
        val ry = right%n
        var x = lx.toInt()
        var y = ly.toInt()

        while(true) {
            if(x.toLong()==rx && y.toLong()==ry) {
                m.add(result[x][y])
                break
            }
            m.add(result[x][y])
            if(y == n-1) {
                x++
                y=0
            } else {
                y++
            }
        }
        answer = m.toIntArray()
        return answer
    }
}

fun main() {
//    val (n, left, right) = Triple(9, 20L, 51L) //
//    val (n, left, right) = Triple(3, 2L, 5L) // 3 2 2 3
    val (n, left, right) = Triple(4, 7L, 14L) // 4 3 3 3 4 4 4 4
    Prog87390().solution(n, left, right)
}