package baekjoon

class Baek1654 {

    fun 근사값찾기(min:Int, max:Int, p:Array<Int>, n:Int): Int {

        var x = 0
        var total = 0
        var maxT = max
        var minT = min

        while(minT < maxT) {
            total = 0
            x = (maxT+minT)/2
            for (a in p) {
                total += a / x
            }

            if (total < n && maxT != x) {
                maxT = x
            } else if (total > n && minT != x) {
                minT = x
            } else {
                return x
            }
        }

        return -1
    }

    fun 정확하게찾기(x:Int, p: Array<Int>, n: Int): Int {

        var result = 0
        var cnt = 0
        //check
        for(i in p) {
            cnt += i/x
        }

        /*
        var t = x
        var total = 0
        var sign = true

        for (a in p) {
            total += a / (t+1)
        }
        if(total >= n) {
            result = t+1
        }

        if(total < n) {
            if(t == 1) {
                return t
            }
            for (a in p) {
                total += a / (t-1)
            }
            sign = false
        }

        do{
            result = t
            total = 0
            if(sign) t++
            else t--

            for (a in p) {
                total += a / t
            }
        }while(total >= n)
         */

        return result
    }
}

fun main() {

    var answer = 0

    val n = 5
    val p = arrayOf(2, 2, 2, 9)

    var min = 1
    var max = p.max()

    val cls = Baek1654()

    answer = cls.근사값찾기(min, max, p, n)
    if(answer != -1) {
        answer = cls.정확하게찾기(answer,p,n)
    }

    println(answer)
}