package baekjoon

class Baek10825 {
}

fun main() {
    var resultArr = mutableListOf<TestResult>()

    resultArr.add(TestResult("Junkyu",   50, 60, 100 ))
    resultArr.add(TestResult("Sangkeun", 80, 60, 50  ))
    resultArr.add(TestResult("Sunyoung", 80, 70, 100 ))
    resultArr.add(TestResult("Soong",    50, 60, 90  ))
    resultArr.add(TestResult("Haebin",   50, 60, 100 ))
    resultArr.add(TestResult("Kangsoo",  60, 80, 100 ))
    resultArr.add(TestResult("Donghyuk", 80, 60, 100 ))
    resultArr.add(TestResult("Sei",      70, 70, 70  ))
    resultArr.add(TestResult("Wonseob",  70, 70, 90  ))
    resultArr.add(TestResult("Sanghyun", 70, 70, 80  ))
    resultArr.add(TestResult("nsj",      80, 80, 80  ))
    resultArr.add(TestResult("Taewhan",  50, 60, 90  ))

    val sorted = resultArr.sortedWith(
        compareByDescending<TestResult> {it.lang}
            .thenBy {it.math}
            .thenByDescending {it.eng}
            .thenBy {it.name}
    )


    for(testResult in sorted) {
        println("${testResult.name}, ${testResult.lang}, ${testResult.math}, ${testResult.eng}  ")
    }

}

class TestResult(
    var name: String,
    var lang: Int,
    var math: Int,
    var eng : Int
)