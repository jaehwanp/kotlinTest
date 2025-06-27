package baekjoon

import java.util.LinkedList
import java.util.Queue

// 단지번호 붙이기
// bfs, dfs

private var n = 0
private var maxI = 0
private var maxJ = 0

class Baek2267 {

    private lateinit var d: Array<Array<Int>>
    private lateinit var group: Array<Array<Int>>
    private val dx = arrayOf(0,0,1,-1)
    private val dy = arrayOf(1,-1,0,0)

    fun printInit() {
        for(a in d) {
            for(b in a) {
                print("${b.toString()},")
            }
            println()
        }
    }

    fun init() {
        d = arrayOf(
            arrayOf(0,1,1,0,1,0,0),
            arrayOf(0,1,1,0,1,0,1),
            arrayOf(1,1,1,0,1,0,1),
            arrayOf(0,0,0,0,1,1,1),
            arrayOf(0,1,0,0,0,0,0),
            arrayOf(0,1,1,1,1,1,0),
            arrayOf(0,1,1,1,0,0,0)
        )
        maxI = d.size
        maxJ = d[0].size

        group = Array(maxI) { Array(maxJ) {0} }

        n = maxI

    }

    fun bfs() {
        var q: Queue<Int> = LinkedList()
    }

    fun dfs(x: Int, y: Int, cnt: Int) {
        group[x][y] = cnt
        for (k in 0..3) {
            val nx = x + dx[k]
            val ny = y + dy[k]
            if (nx in 0 until n && ny in 0 until n) {
                if (d[nx][ny] == 1 && group[nx][ny] == 0) {
                    dfs(nx, ny, cnt)
                }
            }
        }
    }

}

fun main() {
    val cls = Baek2267()
    cls.init()

    for(i in 0 until maxI) {
        for(j in 0 until maxI) {

        }
    }
//    cls.dfs()
}