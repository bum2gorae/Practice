//이중for
var result : String = ""
for (a in 1..100) {
    if (a%2==1) {
        result = ""
        for (b in 1..a) {
            result += "*"
        }
        println(result)
        }
    }


//--------------------------------------------
// 이중 for문을 없애보기
//while
var result: String = "*"
var n = 1
while (n <= 100) {
    if (n % 2 == 1) {
        println(result)
        result = result + "**"
    }
    n++
}

//--------------------------------------------
//do while
var result: String = "*"
var n = 1
do {
    if (n%2 ==1) {
        println(result)
        result = result+"**"
    }
    n++
} while (n <= 100)
