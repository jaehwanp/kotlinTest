package programmers

import java.util.*

class Prog176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        var answer: Array<String> = arrayOf<String>()
        var ma = mutableListOf<String>()
        var q = PriorityQueue<Subject>(compareBy {it.time} )
        var s: Stack<Subject> = Stack()

        plans.forEach {
            q.add(Subject(it[0], it[1].substring(0,2).toInt()*60 + it[1].substring(3,5).toInt(), it[2].toInt()))
        }

        while(q.isNotEmpty()) {
            var a = q.poll()
            var b = q.peek()
            if(b == null) {
                ma.add(a.name)
                break
            }

            if(a.remain > b.time-a.time) {
                a.remain -= b.time-a.time
                s.add(a)
            } else {
                var remainTime = b.time-a.time-a.remain
                if(remainTime >= 0) {
                    ma.add(a.name)
                }
                while (remainTime > 0 && s.isNotEmpty()) {
                    var x = s.pop()
                    if(x.remain <= remainTime) {
                        remainTime -= x.remain
                        ma.add(x.name)
                    } else {
                        x.remain -= remainTime
                        s.add(x)
                        remainTime = 0
                    }
                }

            }
        }

        while(s.isNotEmpty()) {
            ma.add(s.pop().name)
        }

        answer = ma.toTypedArray()

        return answer
    }
}

data class Subject(
    val name: String,
    val time: Int,
    var remain: Int,
)

fun main() {
//    val plans = arrayOf(
//        arrayOf("korean", "11:40", "30"),
//        arrayOf("english", "12:10", "20"),
//        arrayOf("math", "12:30", "40"),
//    )
    val plans = arrayOf(
        arrayOf("bbb", "12:10", "30"),
        arrayOf("ccc", "12:40", "10"),
        arrayOf("aaa", "12:00", "20"),
    )
//    val plans = arrayOf(
//        arrayOf("music", "12:20", "20"),
//        arrayOf("computer", "12:30", "20"),
//        arrayOf("science", "12:40", "50"),
//        arrayOf("history", "14:00", "30"),
//    )
    // korean english math
    Prog176962().solution(plans)
}