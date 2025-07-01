package programmers

class Prog258711 {

    // 미완성

    fun solution(edges: Array<IntArray>): IntArray {
        var answer: IntArray = intArrayOf()

        val graph = mutableMapOf<Int, MutableList<Int>>()
        val visited = mutableSetOf<Int>()

        for((u,v) in edges) {
            graph.getOrPut(u) { mutableListOf() }.add(v)
        }

        graph.forEach{
            dfs(graph, it.key, visited)
            // println(it.toString())
        }

        return answer
    }

    fun dfs(graph:MutableMap<Int, MutableList<Int>>, node: Int, visited: MutableSet<Int>) {
        if(node in visited) return
        println(node)
        visited.add(node)

        for(next in graph.getOrDefault(node, emptyList())) {
            dfs(graph, next, visited)
        }
    }
}

data class Edge(
    val x: Int,
    val y: Int,
    val visited: Boolean = false
)

fun main() {
    val edges = arrayOf(
        intArrayOf(2,3),
        intArrayOf(4,3),
        intArrayOf(1,1),
        intArrayOf(2,1)
    )
//    val edges = arrayOf(
//        intArrayOf(4, 11),
//        intArrayOf(1, 12),
//        intArrayOf(8, 3),
//        intArrayOf(12, 7),
//        intArrayOf(4, 2),
//        intArrayOf(7, 11),
//        intArrayOf(4, 8),
//        intArrayOf(9, 6),
//        intArrayOf(10, 11),
//        intArrayOf(6, 10),
//        intArrayOf(3, 5),
//        intArrayOf(11, 1),
//        intArrayOf(5, 3),
//        intArrayOf(11, 9),
//        intArrayOf(3, 8)
//    )
    // 2 1 1 0
    Prog258711().solution(edges)

    //생성정점 도넛 막대 8자
    // 생성 : 진입 0  진출 2~
    // 도넛 : 진입 1~ 진출 1
    // 막대 : 진입 1~ 진출 0 (마지막)
    // 8자 : 진입 2~ 진출 2
}