package objects

import java.util.Comparator
import List.IUserType



class MyVector(xCordA: Integer =(Math.random * 100).toInt - 100,yCordA: Integer = (Math.random * 100).toInt - 100,
               xCordB: Integer = (Math.random * 100).toInt - 100,
               yCordB: Integer = (Math.random * 100).toInt - 100 ) extends IUserType with Serializable {

  var y = this.yCordB - this.yCordA
  var x = this.xCordB - this.xCordA


  def getA: String = {
    val str = "(" + xCordA + ";" + yCordA + ")"
    str
  }

  def getB: String = {
    val str = "(" + xCordB + ";" + yCordB + ")"
    str
  }

  override def toString: String = "(" + this.x + ";" + this.y + ")"

  override def copy = new MyVector(this.xCordA, this.yCordA, this.xCordB, this.yCordB)

  override def create = new MyVector

  override def getClassName: String = this.getClass.getName

  override def getTypeComparator = new SortMyVector

  override def getCompare: Double = {
    val sqrt = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))
    val length = Math.abs(sqrt)
    length
  }
}

