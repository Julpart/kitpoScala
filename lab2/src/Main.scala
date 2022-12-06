import List.{LinkedList, LinkedListFactory}
import View.View
import objects._

object Main {
  def main(args: Array[String]): Unit = {

    val factory = new LinkedListFactory
    val dot1 = new MyDot(1, 1)
    val list = factory.getBuilderByName(dot1.getClassName)
    for (i <- 0 until 3000) {
        list.add(new MyDot)
    }
    list.printForSort
    list.sort
    list.printForSort




   // val factory = new LinkedListFactory
  //  val w: View = new View(factory)
  //  w.setVisible(true)

  }
}