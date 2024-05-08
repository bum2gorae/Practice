class Car(val model: String, val color: Color = Color.White, var speed: Int = 0, var gas: Int = 0) {
    fun getInfo() {
        println("This Car's model is $model, $color color, Speed is ${getSpeeds()}km now, Remaining gas is ${gas}L")
    }

    fun getSpeeds(): Int {
        return this.speed
    }

    fun getColorCode(): String {
        return color.colorCode
    }

    fun increaseSpeed() {
        when {
            this.gas == 0 -> {
                println("Lack of gas, can't increase speed!")
            }
            else -> {
                this.speed += 10
                this.gas -= 10
            }
        }
    }

    fun addGas(newGas: Int): Unit {
        this.gas += newGas
    }
}

enum class Color(val colorCode: String) {
    Black("#000000"),
    White("#FFFFFF")
}

val myCar = Car("Genesis")
myCar.increaseSpeed()
myCar.addGas(50)
myCar.increaseSpeed()
myCar.increaseSpeed()
myCar.getSpeeds()
myCar.getInfo()
myCar.getColorCode()
