fun decomp(num: Int):List<Int> {
    val result = mutableListOf<Int>()
    var i = 2
    var temp = num
    while (temp!=1) {
        if (temp%i == 0) {
            result.add(i)
            temp = temp/i
        } else i++
    }
    return result
}

println(decomp(60))
