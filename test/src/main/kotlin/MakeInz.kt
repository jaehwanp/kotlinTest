import java.lang.StringBuilder

class MakeInz {
    fun makeSentence(s: String): String {
        var ms = ""
        ms = s.replace("[", "\nintArrayOf(")
            .replace("]", ")")
        return ms
    }

    fun makeSentence2(s:String): String {
        val ss = makeSentence(s.substring(1,s.lastIndex))
        return "arrayOf($ss\n)\n"
    }
}

fun main() {
//    val a1 = MakeInz().makeSentence("[5,5,5,5,5]")
    println(MakeInz().makeSentence2("[[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]]"))
    println(MakeInz().makeSentence2("[[1,2,3],[4,5,6],[7,8,9]]"))
    println(MakeInz().makeSentence2("[[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]"))
    println(MakeInz().makeSentence2("[[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]"))
}