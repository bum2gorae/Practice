class Sort() {
    var isreverse = false
    fun changeMod() {
        this.isreverse = !this.isreverse
    }
    fun sort(input: List<Int>): List<Int> {
        var pivot = input[0]
        val lowList = ArrayList<Int>()
        val highList = ArrayList<Int>()
        for (j in 0 until input.size) {
            if (this.isreverse) {
                if (input[j] > pivot) {
                    lowList.add(input[j])
                } else if (input[j] < pivot) {
                    highList.add(input[j])
                }
            } else {
                if (input[j] < pivot) {
                    lowList.add(input[j])
                } else if (input[j] > pivot) {
                    highList.add(input[j])
                }
            }
        }
        var lowList1: List<Int>
        var highList1: List<Int>
        if (lowList.isNotEmpty()) {
            lowList1 = sort((lowList+pivot))
            highList1 = sort(highList)
        } else {
            lowList1 = (lowList+pivot).toList()
            highList1 = highList.toList()
        }
        val newList = lowList1+highList1
        return newList
    }
}

fun main() {
    var test = Sort()
    test.changeMod()
    val testList = listOf(3, 5, 6, 8, 2, 1)
    val result = test.sort(testList)
    println(result)
}
