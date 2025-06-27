package baekjoon

class Baek2751 {

}

fun main() {
    val personArr = mutableListOf<Person>()

    personArr.add(Person("Junkyu", 23))
    personArr.add(Person("Dohyun", 22))
    personArr.add(Person("Sunyoung", 20))

    personArr.sortBy { it.age }
    for(person in personArr) {
        println("${person.name}, ${person.age}")
    }

}

class Person(var name: String, var age: Int)