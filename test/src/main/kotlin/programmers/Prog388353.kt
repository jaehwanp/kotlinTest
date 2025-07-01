package programmers

import java.util.LinkedList
import java.util.Queue

class Prog388353 {

    fun solution(storage: Array<String>, requests: Array<String>): Int {
        var answer: Int = 0
        val maxX = storage.size
        val maxY = storage[0].length

        for(req in requests) {
            if(req.length == 1) {
                oneReq(storage, req.first(),maxX, maxY)
            } else {
                twoReq(storage, req.first())
            }
        }

        for(i in storage.indices) {
            for(j in storage[i].indices) {
                if(storage[i][j] != '0') {
                    answer++
                }
            }
        }

        return answer
    }

    fun oneReq(storage: Array<String>, req: Char, maxX: Int, maxY: Int) {
        var q: Queue<IntArray>
        var remember = mutableListOf<IntArray>()
        var check: Boolean

        val dx = arrayOf(1,-1,0,0)
        val dy = arrayOf(0,0,1,-1)

        for(x in storage.indices) {
            for(y in storage[x].indices) {
                check = false
                if (storage[x][y] == req) { // 같은거 찾음
                    q = LinkedList()
                    q.add(intArrayOf(x,y))

                    while(!q.isEmpty()) {
                        var qn = q.poll()
                        var qx = qn[0]
                        var qy = qn[1]
                        if(qx == 0 || qx == maxX-1 || qy == 0 || qy == maxY-1) {
                            check = true
                            break
                        }
                        for(i in 0..3) {
                            var nx = qx + dx[i]
                            var ny = qy + dy[i]
                            if(storage[nx][ny] == '0') {
                                q.add(intArrayOf(nx,ny))
                            }
                        }
                    }
                    if(check) {
                        remember.add(intArrayOf(x,y))
                    }
                }
            }
        }

        for(r in remember) {
            val charArray = storage[r[0]].toCharArray()
            charArray[r[1]] = '0'
            storage[r[0]] = String(charArray)
        }

    }

    fun twoReq(storage: Array<String>, req: Char) {
        for(i in storage.indices) {
            var a = storage[i].indices.filter { storage[i][it] == req }
            val charArray = storage[i].toCharArray()
            for(j in a) {
                charArray[j] = '0'
            }
            storage[i] = String(charArray)
        }

    }

}


fun main() {
    val storage: Array<String> = arrayOf("HAH", "HBH", "HHH", "HAH", "HBH")
    val requests: Array<String> = arrayOf("C", "B", "B", "B", "B", "H")

    val answer = Prog388353().solution(storage, requests)
    println(answer)
}

