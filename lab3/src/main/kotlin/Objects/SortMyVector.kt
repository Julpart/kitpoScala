package Objects

import List.IUserType


class SortMyVector : Comparator<IUserType> {
    override fun compare(o1: IUserType, o2: IUserType): Int {
        o1 as MyVector
        o2 as MyVector
        return o1.length.compareTo(o2.length)
    }
}