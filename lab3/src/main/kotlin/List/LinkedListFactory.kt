package List

import Objects.MyDot

import Objects.MyVector


class LinkedListFactory {
    private val list = arrayOf<IUserType>(MyVector(), MyDot())
    private val linkedList: LinkedList<IUserType> = LinkedList<IUserType>()
    private var listClass: IUserType? = null
    private fun findListClass(className: String): IUserType? {
        for (classItem in list) {
            if (classItem.getClassName() == className) {
                listClass = classItem
                return listClass
            }
        }
        listClass = null
        return listClass
    }

    fun getBuilderByName(className: String): LinkedList<IUserType?> {
        val type = findListClass(className)
        val clone: LinkedList<IUserType?> = linkedList.copy()
        clone.setType(type!!)
        return clone
    }

    fun getList(): Array<String?> {
        val items = arrayOfNulls<String>(list.size)
        for (i in list.indices) {
            items[i] = list[i].getClassName()
        }
        return items
    }
}