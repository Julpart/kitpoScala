package objects

import List.IUserType

import java.util.Comparator

trait IUserTypeComparator extends Comparator[IUserType]{
   def compare(o1: IUserType, o2: IUserType): Int
}
