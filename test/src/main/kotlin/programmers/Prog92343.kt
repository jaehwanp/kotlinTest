package programmers

class Prog92343 {

    private lateinit var info: IntArray
    private lateinit var graph: Array<MutableList<Int>>
    private var sumSheep = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        this.info = info
        this.graph = makeAdjacencyList(info, edges)
        dfs(1, 0, graph[0])
        println(sumSheep)
        return sumSheep
    }

    private fun dfs(sheep: Int, wolf: Int, next: MutableList<Int>) {
        if(wolf >= sheep) return
        if(sheep > sumSheep) sumSheep = sheep
        if(next.isEmpty()) return

        for(i in next.indices) {
            val node = next[i]
            val nxt = ArrayList(next) // 깊은 복사
            nxt.removeAt(i)
            nxt.addAll(graph[node])

            if (info[node] == 0) { // 양
                dfs(sheep+1, wolf, nxt)
            } else if (info[node] == 1) { // 늑대
                dfs(sheep, wolf+1, nxt)
            }
        }
    }

    // 인접리스트
    private fun makeAdjacencyList(info: IntArray, edges: Array<IntArray>): Array<MutableList<Int>> {
        val list: Array<MutableList<Int>> = Array(info.size) { mutableListOf() }
        for(intArr in edges) {
            list[intArr[0]].add(intArr[1])
        }
        return list
    }
}

fun main() {
//    Prog92343().solution(
//        intArrayOf(0,0,1,1,1,0,1,0,1,0,1,1),
//        arrayOf(
//            intArrayOf(0,1),
//            intArrayOf(1,2),
//            intArrayOf(1,4),
//            intArrayOf(0,8),
//            intArrayOf(8,7),
//            intArrayOf(9,10),
//            intArrayOf(9,11),
//            intArrayOf(4,3),
//            intArrayOf(6,5),
//            intArrayOf(4,6),
//            intArrayOf(8,9),
//        )
//    ) // 5

    Prog92343().solution(
        intArrayOf(0,1,0,1,1,0,1,0,0,1,0),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(1,3),
            intArrayOf(1,4),
            intArrayOf(2,5),
            intArrayOf(2,6),
            intArrayOf(3,7),
            intArrayOf(4,8),
            intArrayOf(6,9),
            intArrayOf(9,10)
        )
    ) // 5
}