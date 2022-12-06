package List

trait IUserType {


  import java.util.Comparator

  def copy: IUserType

  def create: IUserType

  def getClassName: String

  def getTypeComparator: Comparator[Option[IUserType]]


  def getCompare: Double

}
