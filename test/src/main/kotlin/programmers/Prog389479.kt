package programmers

class Prog389479 {
    // 서버 증설 횟수

    fun solution(players: IntArray, m: Int, k: Int): Int {
        var answer: Int = 0

        var serverList = mutableListOf<Server>()


        for(player in players) {
            var p = player/m

            if(serverList.size < p) {
                for(i in 0 until p-serverList.size) {
                    serverList.add(Server(k))
                    answer++
                }
            }

            if(serverList.size > 0) {
                decrementServerTime(serverList)
            }
        }
        return answer
    }

    private fun decrementServerTime(serverList: MutableList<Server>) {

        for(i in serverList.size-1 downTo 0) {
            var server = serverList[i]
            server.time--
            if(server.time == 0) {
                serverList.removeAt(i)
            }
        }
    }
}

fun main() {
    val players: IntArray = intArrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1)
    val m: Int = 5
    val k: Int = 1

    var answer: Int = 0

    answer = Prog389479().solution(players, m, k)

    println(answer)
}

data class Server(
    var time: Int = 5
)
