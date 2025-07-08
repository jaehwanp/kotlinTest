package programmers

import java.util.LinkedList
import java.util.Stack

class Prog154539 {
    fun solution(numbers: IntArray): IntArray {
        var answer: IntArray = IntArray(numbers.size) {-1}

        for(i in numbers.indices) {
            var temp = 0
            for(j in i+1 until numbers.size) {
                if(numbers[i] < numbers[j]) {
                    answer[i] = numbers[j]
                    break
                }
            }
        }

        for(a in answer) {
            print("$a ")
        }

        return answer
    }

    fun solution2(numbers: IntArray): IntArray {
        var answer: IntArray = IntArray(numbers.size) {-1}
        var temp = 0
        for(i in numbers.indices-1) {
            if(numbers[i] < numbers[i+1]) {
                answer[i] = numbers[i+1]
            }
        }

        return answer
    }
}

fun main() {
    val numbers = intArrayOf(9,1,5,3,6,2)
//    Prog154539().solution(numbers)
    Prog154539().solution2(numbers)
    // -1 5 6 6 -1 -1
}
// 1 1
// .. 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 99
// 1 1 2 2 3 3 .. 99

// 2   2 3 3
