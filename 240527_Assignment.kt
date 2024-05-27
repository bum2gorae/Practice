fun strReverse(input:String): String {
    var newStr :String = ""
    for (i in (input.length - 1) downTo 0) { //뒤에서부터 읽기
        newStr += input[i]
    }
//    for (i in 1..leng) { //앞에서부터할때
//        newStr += input[leng - i]
//    }
    return newStr
}

println(strReverse("hi"))
println(strReverse("easy"))

fun strReverseWord(input:String): String {
    var newStr :String = ""
    var tempStr :String = ""
    for (i in (input.length - 1) downTo 0) {
        if (input[i]==' ') { 
            tempStr = " $tempStr" //띄어쓰기를 만나면 뒤집은 단어 앞에 공백 추가
            newStr = tempStr + newStr //뒤집은 단어를 출력할 문자열 앞에 추가
            tempStr = "" // 뒤집은 단어 리셋
        } else {
            tempStr += input[i]
        }
    }
    newStr = tempStr + newStr
    return newStr
}

println(strReverseWord("on my way"))
