package Objects

import List.IUserType
import java.io.Serializable

class MyVector : IUserType, Serializable {
    private val xCordA: Int
    private val yCordA: Int
    private val xCordB: Int
    private val yCordB: Int
    private val x: Int
    private val y: Int
    val a: String
        get() = "($xCordA;$yCordA)"
    val b: String
        get() = "($xCordB;$yCordB)"
    val length: Double
        get() {
            val sqrt = Math.sqrt(
                Math.pow(
                    x.toDouble(),
                    2.0
                ) + Math.pow(y.toDouble(), 2.0)
            )
            return Math.abs(sqrt)
        }

    constructor(xA: Int, yA: Int, xB: Int, yB: Int) {
        xCordA = xA
        yCordA = yA
        xCordB = xB
        yCordB = yB
        y = yCordB - yCordA
        x = xCordB - xCordA
    }

    constructor() {
        xCordA = (Math.random() * 100).toInt() - 100
        yCordA = (Math.random() * 100).toInt() - 100
        xCordB = (Math.random() * 100).toInt() - 100
        yCordB = (Math.random() * 100).toInt() - 100
        y = yCordB - yCordA
        x = xCordB - xCordA
    }

    override fun toString(): String {
        return "(" + x + ";" + y + ")"
    }

    override fun copy(): IUserType? {
        return MyVector(xCordA, yCordA, xCordB, yCordB)
    }

    override fun create(): IUserType? {
        return MyVector()
    }

    override fun getClassName(): String? {
        return this.javaClass.name
    }

    override fun getTypeComparator(): Comparator<IUserType> {
        return SortMyVector()
    }
}