package programmers

import java.util.*

/*
bfs
 */
class Prog159993 {
    fun solution(maps: Array<String>): Int {
        val s = search(maps, 'S')
        val l = search(maps, 'L')
        val e = search(maps, 'E')

        // S->L
        val sl = bfs(maps, s, 'L')
        if (sl == -1) return -1

        // L->E
        val le = bfs(maps, l, 'E')
        if (le == -1) return -1
        println(sl + le)
        return sl + le
    }

    fun search(maps: Array<String>, target: Char): Pair<Int, Int> {
        return maps.flatMapIndexed {i, row -> row.mapIndexedNotNull {j,c -> if(c==target) i to j else null }}[0]
    }

    fun bfs(maps: Array<String>, start: Pair<Int, Int>, target: Char): Int {
        val maxX = maps.size
        val maxY = maps[0].length
        val visited = Array(maxX) { BooleanArray(maxY) }
        val dx = intArrayOf(1,-1,0,0)
        val dy = intArrayOf(0,0,1,-1)
        val q: Queue<Node> = LinkedList()
        q.offer(Node(start.first, start.second, 0))
        visited[start.first][start.second] = true

        while (q.isNotEmpty()) {
            val nq = q.poll()
            if (maps[nq.x][nq.y] == target) return nq.index
            for (i in 0..3) {
                val nx = nq.x + dx[i]
                val ny = nq.y + dy[i]

                if (nx in 0 until maxX && ny in 0 until maxY &&
                    !visited[nx][ny] && maps[nx][ny] != 'X') {
                    visited[nx][ny] = true
                    q.offer(Node(nx, ny, nq.index + 1))
                }
            }
        }
        return -1
    }
}

class Node (
    val x: Int,
    val y: Int,
    val index: Int,
)

fun main() {
//    Prog159993().solution(arrayOf("SOOOL","XXXXO","OOOOO","OXXXX","OOOOE")) // 16
//    Prog159993().solution(arrayOf("LOOXS","OOOOX","OOOOO","OOOOO","EOOOO")) // -1
    Prog159993().solution(arrayOf("SOOOL", "XXOXX", "XXOOE")) // 10
}
/*
        var answer = 0
        val maxX = maps.size
        val maxY = maps[0].length
        val visited = Array(maxX) { IntArray(maxY) }
        val dx = intArrayOf(1,-1,0,0)
        val dy = intArrayOf(0,0,1,-1)
        val q: Queue<Node> = LinkedList()
        val start = maps.flatMapIndexed {i, row -> row.mapIndexedNotNull {j,c -> if(c=='S') i to j else null }}[0]
        var lNode = Node(0,0,0)

        q.offer(Node(start.first, start.second, 0))

        // 입구 -> 레버
        while(q.isNotEmpty()) {
            val nq = q.poll()
            visited[nq.x][nq.y] = 1

            for(i in 0..3) {
                val nx = nq.x + dx[i]
                val ny = nq.y + dy[i]

                if(nx >= 0 && ny >= 0 && nx < maxX && ny < maxY && visited[nx][ny] == 0) {
                    if (maps[nx][ny] == 'O' || maps[nx][ny] == 'E') {
                        q.offer(Node(nx, ny, nq.index + 1))
                    }

                    if (maps[nx][ny] == 'L') {
                        lNode = Node(nx, ny, nq.index + 1)
                        q.clear()
                        break
                    }
                }
            }
        }
        if(lNode.index == 0) return -1
        answer += lNode.index
        lNode.index = 0
        q.offer(lNode)
        // 레버 -> 출구 (레버 true)
        while(q.isNotEmpty()) {
            val nq = q.poll()
            visited[nq.x][nq.y] = 2

            for(i in 0..3) {
                val nx = nq.x + dx[i]
                val ny = nq.y + dy[i]

                if(nx >= 0 && ny >= 0 && nx < maxX && ny < maxY &&
                   visited[nx][ny] < 2
                ) {
                    if (maps[nx][ny] == 'O' || maps[nx][ny] == 'S') {
                        q.offer(Node(nx, ny, nq.index + 1))
                    }

                    if (maps[nx][ny] == 'E') {
                        lNode = Node(nx, ny, nq.index + 1)
                        q.clear()
                        break
                    }
                }
            }
        }
        if(lNode.index == 0) return -1
        answer += lNode.index
        println(answer)
        return answer
 */