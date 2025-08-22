package programmers

/*


64 -> 내림

56 -> 올림
55 -> 올림
54 -> 내림

45 -> 내림
15 -> 내림
17 -> 올림
13 -> 내림

16 26 36 46 56 66 .. 올림

15 25 35 45 내림
55 65 75 85 올림

14 24 34 44 54 64 내림
 */
import kotlin.math.min

class Solution {
    fun solution(storey: Int): Int =
        if (storey < 10) min(storey, 11 - storey)
        else min(storey % 10 + solution(storey / 10), 10 - storey % 10 + solution(storey / 10 + 1))
}


class Prog148653 {
    fun solution(storey: Int): Int {
        var s = storey
        var answer = 0

        while (s > 0) {
            val digit = s % 10
            val next = (s / 10) % 10

            if (digit > 5 || (digit == 5 && next >= 5)) {
                answer += 10 - digit
                s += 10 // 올림 처리
            } else {
                answer += digit
            }

            s /= 10
        }

        return answer
    }

    fun test(storey: Int, result: Int) {
        val a = solution(storey)
        if(a == result) {
            print("OOO ")
        } else {
            print("XXX ")
        }
        println("${storey.toString().padStart(10, ' ')} ${a.toString().padStart(3, ' ')} $result")
    }
}

fun main() {
    Prog148653().test(16, 6)
    Prog148653().test(2554, 16)
    Prog148653().test(909, 4)
    Prog148653().test(5555, 18)
    Prog148653().test(99, 2)
    Prog148653().test(80, 3)
    Prog148653().test(165, 10)
    Prog148653().test(65515, 19)
}
/*
909 4
5555 18
99 2
80 3
165 10
 */