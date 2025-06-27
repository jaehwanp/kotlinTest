package baekjoon

import java.util.LinkedList
import java.util.Queue

class Baek7576 {

}

fun main() {

    var p = arrayOf(
        arrayOf(-1, 1 , 0,  0, 0),
        arrayOf(0 ,-1 ,-1, -1, 0),
        arrayOf(0 ,-1 ,-1, -1, 0),
        arrayOf(0 ,-1 ,-1, -1, 0),
        arrayOf(0 , 0 , 0,  0, 0),
    )

    var n = p.size
    var m = p[0].size

    val dx = arrayOf(0,0,1,-1)
    val dy = arrayOf(1,-1,0,0)

    var q: Queue<Tomato> = LinkedList()

    // 익은 토마토 확인
    for(i in p.indices) {
        for(j in p[i].indices) {
            if(p[i][j] == 1) {
                q.add(Tomato(i,j,0))
            }
        }
    }

    var depth = 0

    while (!q.isEmpty()) {

        var t = q.poll()
        var tx = t.x
        var ty = t.y

        for(k in 0 until 4) {
            var nx = tx + dx[k]
            var ny = ty + dy[k]

            if(nx in 0 until n && ny in 0 until m && p[nx][ny] == 0) {
                p[nx][ny] = 1
                q.add(Tomato(nx,ny,t.depth+1))
                depth = t.depth+1
            }
        }
    }

    for(i in p.indices) {
        for(j in p[i].indices) {
            if(p[i][j] == 0) {
                depth = -1
            }
        }
    }

    println(depth)



}

data class Tomato (
    val x: Int,
    val y: Int,
    val depth: Int
)




