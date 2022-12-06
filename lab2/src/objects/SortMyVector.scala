package objects

import List.IUserType
import objects.MyVector

import java.util.Comparator

class SortMyVector extends Comparator[Option[IUserType]] {
  override def compare(o1: Option[IUserType], o2: Option[IUserType]): Int = {
    val vector1 = o1.asInstanceOf[IUserType]
    val vector2 = o2.asInstanceOf[IUserType]
    vector1.getCompare.compareTo(vector2.getCompare)
  }
}
