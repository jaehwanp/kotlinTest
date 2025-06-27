package baekjoon

class Baek11047 {
}

fun main() {
    var k = 4200
    var p = arrayOf(
        1,
        5,
        10,
        50,
        100,
        500,
        1000,
        5000,
        10000,
        50000
    )
    p.sortDescending()

    var a = 0

    for(i in 0 until p.size) {
        if(p[i] < k) {
            var def = k/p[i]
            a += def
            k -= p[i]*def
        }
    }

    println(a)

}