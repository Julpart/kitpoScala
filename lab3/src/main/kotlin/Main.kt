import List.LinkedListFactory
import Objects.MyDot
import View.View

fun main(args: Array<String>) {

    val factory = LinkedListFactory()
    var dot = MyDot(1,1)
    var list = factory.getBuilderByName(dot.getClassName()!!)
    for (i in 0..100000) {
            list.add(MyDot())

    }
    list.printForSort()
    list.sort()
    list.printForSort()



//    val w = View()
 //  w.setVisible(true)

}