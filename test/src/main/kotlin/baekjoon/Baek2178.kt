package baekjoon

import java.util.LinkedList
import java.util.Queue

class Baek2178 {
    // bfs visit 를 안만들고하자.
}

fun main() {

    var p = arrayOf(
        arrayOf(1,1,0,1,1,0),
        arrayOf(1,1,0,1,1,0),
        arrayOf(1,1,1,1,1,1),
        arrayOf(1,1,1,1,0,1),
    )
    val n = 4
    val m = 6

    val dx = arrayOf(1,-1,0,0)
    val dy = arrayOf(0,0,1,-1)

    var q: Queue<Node> = LinkedList()
    q.add(Node(0,0, 1))

    while(!q.isEmpty()) {

        var qn = q.poll()
        var qx = qn.x
        var qy = qn.y
        var qDepth = qn.depth

        if(qx == n-1 && qy == m-1) {
            println("$qDepth")
            q.clear()
            break
        }

        p[qx][qy] = 2

        for(k in 0..3) {
            var nx = qx + dx[k]
            var ny = qy + dy[k]

            if(nx in 0 until n && ny in 0 until m && p[nx][ny] == 1) {
                q.add(Node(nx, ny, qDepth++))
            }
        }

    }

}

data class Node(
    val x: Int,
    val y: Int,
    val depth: Int
)