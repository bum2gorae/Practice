var result1 = 0
var result2 = 0
var result3 = 0
var c = 1

for (a in 1..100) {
    if ( a%2 == 0) {
        result1 = result1 + a
    } else {
        continue
    }
}
println(result1)

for (b in 1..100) {
    when (b%2) {
        0 -> result2 += b
        else -> continue
    }
}
println(result2)

while (c <= 100) {
    result3 = if(c%2==0) result3+c else result3
    c++
}
println(result3)


//fizzbuzz
for (f in 1..100) {
    when (f%3) {
        0 -> if (f%5 ==0) println("FizzBuzz") else println("Fizz") 
        else -> if (f%5 ==0) println("Buzz") else println(f)
    }
}
