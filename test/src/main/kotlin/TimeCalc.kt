import programmers.Prog181187
import kotlin.system.measureNanoTime

class TimeCalc {
}

fun main() {
    val elapsed = measureNanoTime {
        // 측정할 코드
//        Prog181187().solution(40,100)
    }
    println("실행 시간1: ${elapsed}ms")

    val elapsed2 = measureNanoTime {
        // 측정할 코드
        Prog181187().solution1(40,100)
    }
    println("실행 시간2: ${elapsed2}ms")
}