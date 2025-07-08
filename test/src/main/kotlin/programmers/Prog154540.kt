package programmers

class Prog154540 {

    private var cnt = 0

    fun solution(maps: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        val maxX = maps.size
        val maxY = maps[0].length
        var visited : Array<Array<Boolean>> = Array(maxX) { Array(maxY) {false} }

        for(x in 0 until maxX) {
            for(y in 0 until maxY) {
                if(!visited[x][y] && maps[x][y] != 'X') {
                    cnt = 0
                    dfs(maps, visited, x, y)
                    answer.add(cnt)
                }
            }
        }
        if(answer.size == 0) answer.add(-1)
        answer.sort()
        return answer.toIntArray()
    }

    fun dfs(maps: Array<String>, visited: Array<Array<Boolean>> ,x: Int, y: Int) {
        if(visited[x][y]) return
        visited[x][y] = true
        val dx = arrayOf(1,-1,0,0)
        val dy = arrayOf(0,0,-1,1)
        cnt += maps[x][y].digitToInt()

        if(maps[x][y] != 'X') {
            for(i in 0..3) {
                val nx = dx[i]+x
                val ny = dy[i]+y
                if(nx in 0 until maps.size && ny in 0 until maps[0].length) {
                    if(!visited[nx][ny] && maps[nx][ny] != 'X') {
                        dfs(maps, visited, nx, ny)
                    }
                }
            }
        }
    }
}


fun main() {
    val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
    // 1 1 27
    Prog154540().solution(maps)
}

