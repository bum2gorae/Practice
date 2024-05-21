import com.sun.tools.classfile.Module_attribute.ProvidesEntry.length

practice()

fun practice() {
    println("== 변수 활용 ==")
    repeat(5) { swap((1..100).random(), (1..100).random()) }

    println("== 조건문 활용 ==")
    repeat(5) {
        val score = (50..100).random()
        println("$score 의 등급은 ${convertToGrade(score)} 입니다.")
    }

    println("== 반복문 조건문 활용 ==")
    val wordList = listOf("intel", "Android", "easy", "hello world")
    wordList.forEach { println("${it}'s uppercase is ${getUppercase(it)}") }

}

fun swap(input1: Int, input2: Int) {
    var a = input1
    var b = input2
    println("swap 전 - a: $a, b: $b")
    //
    //TODO: a, b 의 값을 변경 값 출력.
    //
    val temp = a
    a = b
    b = temp
    println("swap 후 - a: $a, b: $b")
}

fun convertToGrade(input: Int): String {
    //
    //TODO: 90 점 이상이면 A, 80이상 90미만 B, 70이상 80미만 C, 60이상 70미만 D, 60미만 F
    //
    val result: String = when (input/10) {
        10,9 -> "A"
        8 -> "B"
        7 -> "C"
        6 -> "D"
        else -> "F"
    }
    return result
}

fun getUppercase(input: String): String {
    //
    //TODO: 입력받은 단어를 전부 대문자로 바꾸는 함수
    // 주의 - uppercase() 함수 사용 금지
    //
    val alphaArray = "abcdefghijklmnopqrstuvwxyz "
    val upperAlphaArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
    var result: String = ""
    for (i in 0 until input.length) {
        for (j in 0..26) {
            if (upperAlphaArray[j]==input[i]) {
                result += upperAlphaArray[j].toString()
            } else if (alphaArray[j]==input[i]) {
                result += upperAlphaArray[j].toString()
            }
        }
    }
    return result
}

var test2 = convertToGrade(98)
println(test2)
var test3 = getUppercase("stUdy")
println(test3)

