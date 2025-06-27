package baekjoon

import kotlin.random.Random

class Baek11652 {
}

fun main() {

    var cardList = mutableListOf<Long>()
    var card: Long

    for(i in 0..100) {
        card = Random.nextLong()/100000000000000000
        cardList.add(card)
    }

    println(cardList.joinToString("\n"))
}


data class metCard (
    val num: Long,
    val met: Int
 )