package baekjoon

import java.util.*

class Baek1260 {
    // DFS BFS
    // 인접리스트 방식
    private val nodeNum = 5
    private lateinit var lineArr: MutableList<MutableList<Int>>
    private lateinit var c: BooleanArray

    fun init() {

        val edges = listOf(
            5 to 4,
            5 to 2,
            1 to 2,
            3 to 4,
            3 to 1
        )

        var nodeNum: Int = edges.size
        lineArr = MutableList(nodeNum+1) { mutableListOf<Int>() }
        c = BooleanArray(nodeNum+1)

        for((from, to) in edges) {
            lineArr[from].add(to)
            lineArr[to].add(from)
        }
    }

    fun printGraph() {
        for(line in lineArr) {
            println(line.toString())
        }
    }

    fun dfs(start: Int) {
        if(c[start]) {
            return
        }

        c[start] = true
        print("$start ")

        for(y: Int in lineArr[start]) {
            if(!c[y]) {
                dfs(y)
            }
        }
    }

    fun bfs(start: Int) {

        var q: Queue<Int> = LinkedList()

        q.add(start)
        c[start] = true

        while (!q.isEmpty()) {
            var x: Int = q.remove()
            print("$x ")

            for(y: Int in lineArr[x]) {
                if(!c[y]) {
                    q.add(y)
                    c[y] = true
                }
            }
        }



    }

}

fun main() {
    val graph = Baek1260()

    var start = 3

    graph.init()
//    graph.printGraph()

    graph.dfs(start)

    println()

    graph.init()
//    graph.printGraph()

    graph.bfs(start)
}

/*
정점, 시작정점
4 1

간선
1 2
1 3
1 4
2 4
3 4

 */