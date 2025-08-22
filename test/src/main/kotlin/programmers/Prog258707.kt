package programmers


class Prog258707 {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        val deck = mutableListOf<Int>()
        val temp = mutableListOf<Int>()
        val target = n+1
        var index = 0
        var round = 1
        var remainCoin = coin
        // 처음 뽑기
        for((i,c) in cards.withIndex()) {
            if(i >= n/3) {
                index = i
                break
            }
            deck.add(c)
        }

        while(index+2 <= cards.size) {
            // 2장 뽑기
            for (i in index until index + 2) {
                deck.add(cards[i])
                temp.add(cards[i])
                index++
            }

            // 다음 라운드로 가는 가능한 수 찾기
            val pair = findTargetSum(deck, target)

            // 가능한 수 찾음
            if (pair.first != 0) {
                if(temp.contains(pair.first)) {
                    remainCoin--
                    temp.remove(pair.first)
                }
                if(temp.contains(pair.second)) {
                    remainCoin--
                    temp.remove(pair.second)
                }
                // 코인부족
                if(remainCoin < 0) break

                deck.remove(pair.first)
                deck.remove(pair.second)
                round++
            } else {
                // 가능한 수 못찾음
                break
            }
        }
        return round
    }

    // 가능한 수 찾기
    private fun findTargetSum(deck: MutableList<Int>, target: Int): Pair<Int, Int> {
        var pair = Pair(0,0)
        for (num in deck) {
            val complement = target - num
            if(deck.contains(complement)) {
                pair = Pair(num, complement)
                break
            }
        }
        return pair
    }
}
/*

1 / 3 6 7 2 / 1 10 / - 6 7
2 / 3 2 10 / 5 9 / - 10 3
3 / 2 5 / 8 12 / - 5 8
4 / 2 / 11 4 / - 2 11
5 / Rmx
 */

fun main() {
    Prog258707().solution(4, intArrayOf(3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4)) // 5
//    Prog258707().solution(3, intArrayOf(1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12)) // 2
//    Prog258707().solution(2, intArrayOf(5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7)) // 2
//    Prog258707().solution(10, intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)) // 2
//    Prog258707().solution(99, intArrayOf(1,18, 2,17, 3,16, 4,15, 5,14, 6,13, 7,12, 8,11, 9,10)) // 7
//    Prog258707().solution(2, intArrayOf(1,2,3,4,5,6)) // 2
//    Prog258707().solution(8, intArrayOf(1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7)) // 5
}