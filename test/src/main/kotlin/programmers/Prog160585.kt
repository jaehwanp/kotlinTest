package programmers
// 문제 잘못된듯?
class Prog160585 {

    fun solution(board: Array<String>): Int {
        var answer: Int = 0
        var checkNum = numCheck(board)

        if(checkNum != -1 && gameSet(board, checkNum)) {
            answer = 1
        }

        return answer
    }

    fun gameSet(board: Array<String>, checkNum: Int): Boolean {
        var list = mutableListOf<Char>()

        // 가로세로 검사
        for (i in 0..2) {
            if (board[i][0] != '.' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) list.add(board[i][0])
            if (board[0][i] != '.' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) list.add(board[0][i])
        }

        // 대각선 검사
        if (board[0][0] != '.' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) list.add(board[0][0])
        if (board[0][2] != '.' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) list.add(board[0][2])

        if(list.size > 1) return false
        if(list.size == 1 && list[0] == 'O' && checkNum == 1) return true
        if(list.size == 1 && list[0] == 'X' && checkNum == 0) return true
        if(list.size == 0 && (checkNum == 0 || checkNum == 1)) return true

        return false
    }

    // O가 X보다 1 많음 : 1 / O와 X가 같음 : 0 / -1
    fun numCheck(board: Array<String>): Int {
        var result = 0
        val maxX = board.size
        val maxY = board[0].length
        var cntO = 0
        var cntX = 0

        for(x in 0 until maxX) {
            for(y in 0 until maxY) {
                if(board[x][y] == 'O') {
                    cntO++
                } else if (board[x][y] == 'X') {
                    cntX++
                }
            }
        }

        result = when (cntO) {
            cntX -> 0
            cntX+1 -> 1
            else -> -1
        }
        return result
    }

}
/*

 */

fun main() {
    var boardList = arrayOf(
//        arrayOf("OOO", "XOX", "XXO"), //     1  0
//        arrayOf("OXO", "XOX", "OXO"), //     1  0
        arrayOf("...", ".OO", "..."), //     0  0
    )

    for(board in boardList) {
        println(Prog160585().solution(board))

    }
}