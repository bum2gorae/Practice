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
