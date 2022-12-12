package Objects

import List.IUserType
import java.io.Serializable


class MyDot : IUserType, Serializable {
    val x: Int
    val y: Int
    val range: Int
        get() = Math.abs(x) + Math.abs(y)

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor() {
        x = (Math.random() * 100).toInt() - 100
        y = (Math.random() * 100).toInt() - 100
    }

    override fun toString(): String {
        return "X: " + x + " Y: " + y
    }

    override fun copy(): IUserType? {
        return MyDot(x, y)
    }

    override fun create(): IUserType? {
        return MyDot()
    }

    override fun getClassName(): String? {
        return this.javaClass.name
    }

    override fun getTypeComparator(): Comparator<IUserType> {
        return SortMyDot()
    }
}
