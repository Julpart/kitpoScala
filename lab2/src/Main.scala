import List.{LinkedList, LinkedListFactory}
import View.View
import objects._

object Main {
  def main(args: Array[String]): Unit = {

    val factory = new LinkedListFactory
    val dot1 = new MyDot(1, 1)
    val list = factory.getBuilderByName(dot1.getClassName)
    for (i <- 0 until 100000) {
      if(i%100==0) {
        list.addInNewList(new MyDot)
      }else{
        list.add(new MyDot)
      }
    }
    var startTimeMillis = System.currentTimeMillis()
    for (i <- 0 until 100000) {
      list.set(i/2,new MyDot)
    }
    var endTimeMillis = System.currentTimeMillis()
    System.out.println("New: " + (endTimeMillis - startTimeMillis))
    startTimeMillis = System.currentTimeMillis()
    for (i <- 0 until 100000) {
      list.setOld(i / 2, new MyDot)
    }
    endTimeMillis = System.currentTimeMillis()
    System.out.println("Old: " + (endTimeMillis - startTimeMillis))




   // val factory = new LinkedListFactory
  //  val w: View = new View(factory)
  //  w.setVisible(true)

  }
}