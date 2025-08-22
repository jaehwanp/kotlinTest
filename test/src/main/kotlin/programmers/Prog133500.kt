package programmers

import java.util.*

class Prog133500 {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        val list = getList(n, lighthouse)
        val visited = BooleanArray(n+1)
        println(dfs(list, visited, 1, 0))
//        bright[1] = false
//        dfs(list, visited, bright, 1)
        return 1
    }

    // 인접 리스트 변경
    private fun getList(n: Int, lighthouse: Array<IntArray>): Array<MutableSet<Int>> {
        val list = Array(n+1) { mutableSetOf<Int>() }
        for(i in lighthouse) {
            list[i[0]].add(i[1])
            list[i[1]].add(i[0])
        }
        list.forEachIndexed { index, ints -> println("$index ${ints.toString()}") }
        return list
    }

    private fun dfs(list: Array<MutableSet<Int>>, visited: BooleanArray, start: Int, result: Int): Int {
        if(visited[start]) {
            return 1
        }
        var a = result
        visited[start] = true
        for(i in list[start]) {

            a += dfs(list, visited, i, result)

            a += dfs(list, visited, i, result)
        }
        return a
    }

}
/*
1 [4, 5, 2, 3]
2 [1, 9]
3 [1]
4 [1]
5 [1, 6]
6 [5, 7, 8]
7 [6]
8 [6]
9 [2, 10]
10 [9]

켰다면
1' 4'
1' 6'
1' 9'

껏다면?
1 4'
1 5' 6 7' 8'
1 3'
1 2' 9 10'

1' 4
1' 5 6' 7 8
1' 3
1' 2 9' 10

 */

fun main() {
//    Prog133500().solution(8,
//        arrayOf(
//            intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(1, 4), intArrayOf(1, 5), intArrayOf(5, 6), intArrayOf(5, 7), intArrayOf(5, 8)
//        )
//    ) // 2

    Prog133500().solution(10,
        arrayOf(
            intArrayOf(4, 1), intArrayOf(5, 1), intArrayOf(5, 6), intArrayOf(7, 6), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(6, 8), intArrayOf(2, 9), intArrayOf(9, 10)
        )
    ) // 3
}