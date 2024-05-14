//constructer error

enum class MbtiEnum(var mbtiName: Int, var mbtiDcrb: Int) {
    ESFJ(1, 11),
    ENFJ(2, 22),
    ESTJ(3, 33),
    ENTJ(4, 44),
    ESFP(5, 55),
    ENFP(6, 66),
    ESTP(7, 77),
    ENTP(8, 88),
    ISFJ(9, 99),
    INFJ(10, 1010),
    ISTJ(11, 1111),
    INTJ(12, 1212),
    ISFP(13, 1313),
    INFP(14, 1414),
    ISTP(15, 1515),
    INTP(16, 1616);


    fun getMbtiTitle(): Int {
        this.mbtiName = mbtiName
        return mbtiName
    }

    fun getMbtiDescribe(): Int {
        this.mbtiDcrb = mbtiDcrb
        return mbtiDcrb
    }
}

open class Mbti(ie:String, sn:String, tf:String, jp:String) {
    val mbtiComb: String = ie+sn+tf+jp
    class MbtiValue: Mbti {
        constructor(mbticomb: String):this()
        val mbtiEnumStr = MbtiEnum.valueOf(mbtiComb.uppercase())
    }
}
val ESFJ = Mbti("E","s","f","j")
println(ESFJ.)
