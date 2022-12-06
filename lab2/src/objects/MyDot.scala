package objects

import List.IUserType
import java.io.Serializable
import java.util.Comparator

class MyDot(var xCord: Int = (Math.random * 100).toInt - 100,
            var yCord: Int = (Math.random * 100).toInt - 100) extends IUserType with Serializable {


  def getX: Integer = this.xCord

  def getY: Integer = this.yCord

  override def getCompare: Double = {
    val range = Math.abs(this.xCord) + Math.abs(this.yCord)
    range
  }



  override def toString: String = "X: " + this.xCord + " Y: " + this.yCord

  override def copy = new MyDot(this.xCord, this.yCord)

  override def create = new MyDot

  override def getClassName: String = this.getClass.getName

  override def getTypeComparator = new SortMyDot
}
