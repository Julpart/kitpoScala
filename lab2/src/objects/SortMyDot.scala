package objects

import List.IUserType
import objects.MyDot

import java.util.Comparator

class SortMyDot extends Comparator[Option[IUserType]] {
  override def compare(o1: Option[IUserType], o2: Option[IUserType]): Int = {
    val dot1 = o1.asInstanceOf[IUserType]
    val dot2 = o2.asInstanceOf[IUserType]
    dot1.getCompare.compareTo(dot2.getCompare)
  }
}