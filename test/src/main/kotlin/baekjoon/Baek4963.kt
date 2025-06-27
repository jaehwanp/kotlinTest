package baekjoon

import java.util.*

class Baek4963 {

    val dx = arrayOf(1,-1, 0, 0, 1, 1,-1,-1)
    val dy = arrayOf(0, 0, 1,-1, 1,-1, 1,-1)

    fun dfs(x: Int, y:Int, m: Array<Array<Int>>, visited: Array<Array<Int>>) {

        visited[x][y] = 1

        for(k in 0..7) {
            var nx = x + dx[k]
            var ny = y + dy[k]

            if( nx in m.indices &&
                ny in m[nx].indices &&
                m[nx][ny] == 1 &&
                visited[nx][ny] == 0) {
                dfs(nx,ny,m,visited)
            }
        }
    }

    fun bfs(x: Int, y:Int, m:Array<Array<Int>>, visited: Array<Array<Int>>) {

        var q: Queue<Array<Int>> = LinkedList()

        visited[x][y] = 1
        q.add(arrayOf(x,y))

        while(!q.isEmpty()) {
            var qn = q.poll()
            var qx = qn[0]
            var qy = qn[1]

            for(k in 0..7) {
                var nx = qx + dx[k]
                var ny = qy + dy[k]

                if( nx in m.indices &&
                    ny in m[nx].indices &&
                    m[nx][ny] == 1 &&
                    visited[nx][ny] == 0) {

                    visited[nx][ny] = 1
                    q.add(arrayOf(nx, ny))
                }
            }
        }


    }
}

fun main() {

    val m = arrayOf(
        arrayOf(1, 0, 1, 0, 1),
        arrayOf(0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 0, 1),
        arrayOf(0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 0, 1),
    )

    val w = m[0].size
    val h = m.size

    var visited = Array(h) {Array(w) {0} }

    var dfsCnt = 0
    var bfsCnt = 0
    val cls = Baek4963()

    for(i in m.indices) {
        for(j in m[i].indices) {
            //dfs
            if(m[i][j] == 1 && visited[i][j] == 0) {
                cls.dfs(i,j,m,visited)
                dfsCnt++
            }
        }
    }

    visited = Array(h) {Array(w) {0} }

    for(i in m.indices) {
        for(j in m[i].indices) {
            //bfs
            if(m[i][j] == 1 && visited[i][j] == 0) {
                cls.bfs(i,j,m,visited)
                bfsCnt++
            }
        }
    }

    println(dfsCnt)
    println(bfsCnt)

}