package List

import List.IUserType
import objects.{MyDot, MyVector}

class LinkedListFactory {
  private var list = Array(new MyVector, new MyDot)
  private var linkedList = new LinkedList[IUserType]()
  private var listClass = list(1)

  private def findListClass(className: String): IUserType = {
    for (classItem <- list) {
      if (classItem.getClassName == className) {
        listClass = classItem
        return listClass
      }
    }
    listClass = null
    listClass
  }

  def getBuilderByName(className: String):  LinkedList[IUserType] = {
    val listtype = findListClass(className)
    val clone = linkedList.copy
    clone.setType(listtype)
    clone
  }

  def getList: Array[String] = {
    val items = new Array[String](list.length)
    for (i <- 0 until list.length) {
      items(i) = list(i).getClassName
    }
    items
  }
}