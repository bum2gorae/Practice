abstract class Vehicle(var model: String) {
    abstract var maxSpeed: Int
    fun startEngine() {
        println("엔진 시작")
    }

    fun startMove() {
        println("이동 시작")
    }

}

interface FlyEngine {
    fun fly() {
        takeOff()
        println("비행")
    }

    fun takeOff()
    fun land()
}

interface Door {
    fun openDoor() {
        println("문이 열렸어요")
    }
}

class Airplane: Vehicle("airbus"), FlyEngine, Door {
    override fun takeOff() {
        println("비행기 이륙")
    }

    override fun land() {
        println("비행기 착륙")
    }

    override var maxSpeed: Int = 10000
}

class Heli: Vehicle("Heli"), FlyEngine {
    override var maxSpeed: Int = 10000
    override fun takeOff() {
        println("헬기 이륙")
    }

    override fun land() {
        println("헬기 착륙")
    }

}


class Car(model:String): Vehicle(model), Door {
    override var maxSpeed: Int = 10000

}

var v1 = Car()
v1.fly()

var v2 = Heli()
v2.fly()
v2.takeOff()

var v3 = Airplane()
v3.fly()

var v = Vehicle()
