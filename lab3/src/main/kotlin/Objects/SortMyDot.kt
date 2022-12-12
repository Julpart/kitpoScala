package Objects

import List.IUserType


class SortMyDot : Comparator<IUserType> {
    override fun compare(o1: IUserType, o2: IUserType): Int {
        o1 as MyDot
        o2 as MyDot
        return o1.range.compareTo(o2.range)
    }
}