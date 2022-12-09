package List

import scala.collection.mutable.ListBuffer
import scala.{+:, ::, List}
import scala.util.control.Breaks._


class Node[V](var data: Option[V], var next: Node[V] , var prev: Node[V] ) {
}

class HeadNode[V](var data: Node[V], var next: HeadNode[V], var prev: HeadNode[V]) {
}



class LinkedList[V <: IUserType](head: HeadNode[V] =  new HeadNode[V](new Node[V](null ,null,null), null, null)){




  head.next = head
  head.prev = head
  head.data.next = head.data
  head.data.prev = head.data
  private var currHeadNode = head
  private var size = 0
  private var nodetype:IUserType = null
  def getSize: Int = size

  import java.util.Comparator

  var comparatorType: Comparator[Option[IUserType]] = null


  def setType(nodetype: IUserType): Unit = {
    this.nodetype = nodetype
    this.comparatorType = nodetype.getTypeComparator
  }



  def copy = new LinkedList[IUserType]()

  def getType: IUserType = this.nodetype

  private def addBefore(data: V, node: Node[V]) = {
    var new_node = new Node(Option(data), node, node.prev)
    new_node.prev.next = new_node
    new_node.next.prev = new_node
    size += 1
    new_node
  }



  def addInNewList(data: V): Boolean = if (nodetype != null) if (data.getClassName.equals(nodetype.getClassName)) {
    val HeadNode = new HeadNode[V](new Node(null, null, null), head, currHeadNode)
    this.currHeadNode.next = HeadNode
    this.currHeadNode = HeadNode
    this.head.prev = this.currHeadNode
    this.currHeadNode.data.next = this.currHeadNode.data
    this.currHeadNode.data.prev = this.currHeadNode.data
    addBefore(data, this.currHeadNode.data)
    true
  }
  else false
  else {
    val HeadNode = new HeadNode[V](new Node(null, null, null), head, currHeadNode)
    this.currHeadNode.next = HeadNode
    this.currHeadNode = HeadNode
    this.head.prev = this.currHeadNode
    this.currHeadNode.data.next = this.currHeadNode.data
    this.currHeadNode.data.prev = this.currHeadNode.data
    addBefore(data, this.currHeadNode.data)
    true
  }

  def add(data: V): Boolean = if (nodetype != null) if (data.getClassName.equals(nodetype.getClassName)) {
    if (this.currHeadNode eq this.head) {
      addInNewList(data)
      return true
    }
    addBefore(data, this.currHeadNode.data)
    true
  }
  else false
  else {
    if (this.currHeadNode eq this.head) {
      addInNewList(data)
      return true
    }
    addBefore(data, this.currHeadNode.data)
    true
  }

  def addByIndex(data: V, id: Int): Boolean = if (nodetype != null) if (data.getClassName.equals(nodetype.getClassName)) {
    addBefore(data, index(id))
    true
  }
  else false
  else {
    addBefore(data, index(id))
    true
  }

  def remove: Boolean = {
    removeNode(currHeadNode.data.prev)
    true
  }

  import java.util.NoSuchElementException

  private def removeNode(node: Node[V]): Boolean = {
    if ((node eq currHeadNode.data.next) && (node eq currHeadNode.data.prev)) if (currHeadNode eq head) throw new NoSuchElementException
    else {
      currHeadNode.prev.next = currHeadNode.next
      currHeadNode.next.prev = currHeadNode.prev
      currHeadNode = currHeadNode.prev
      return true
    }
    node.prev.next = node.next
    node.next.prev = node.prev
    node.next = null
    node.prev = null
    node.data = null
    size -= 1
    true
  }

  def removeByIndex(id: Int): Boolean = {
    removeNode(index(id))
    true
  }

  def printForSort(): Unit = {
    var head = this.head.next
    var currNode = head.data.next
    var i = 0
    breakable {while ( {
      true
    }) {
      if (currNode eq head.data) {
        head = head.next
        if (head eq this.head) break
        currNode = head.data.next
      }
      System.out.println(i + ")" + currNode.data)
      i += 1
      currNode = currNode.next
    }
  }
}



  private def index(index: Int) = {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size)
    var headNode = head.next
    var node = headNode.data.next
    for (i <- 0 until index) {
      node = node.next
      if (node eq headNode.data) {
        headNode = headNode.next
        node = headNode.data.next
      }
    }
    node
  }

  def get(id: Int): Option[V] = index(id).data

  def set(id: Int, data: V): Unit = {
    var node = index(id)
    node.data = Option(data)

  }

  def clean(): Unit = {
    var i = 0
    val allsize = this.size
    while ( {
      allsize != i
    }) {
      removeNode(currHeadNode.data.prev)
      i+=1
    }
    this.nodetype = null
    this.comparatorType = null

  }
  def mergeSort(list: ListBuffer[V]):ListBuffer[V]= {
      if(list.size <= 1){
        return list
      }else{
        val sortedList = new ListBuffer[V]
        var leftList = list.slice(0,list.size/2)
        var rightList = list.slice(list.size/2,list.size)

        leftList = mergeSort(leftList)
        rightList = mergeSort(rightList)

        var i = 0
        var j = 0
        while ( {
          i < leftList.size && j < rightList.size
        }) {
          if (leftList(i).getCompare <= rightList(j).getCompare) {
            sortedList.addOne(leftList(i))
            i += 1
          }
          else {
            sortedList.addOne(rightList(j))
            j += 1
          }
        }
        while ( {
          i < leftList.size
        }) {
          sortedList.addOne(leftList(i))
          i += 1
        }
        while ( {
          j < rightList.size
        }) {
          sortedList.addOne(rightList(j))
          j += 1
        }
        sortedList
      }
  }

  def sort: Unit ={
    var list = new ListBuffer[V]
    var head = this.head.next
    var currNode = head.data.next
    var i = 0
    breakable {
      while ( {
        true
      }) {
        if (currNode eq head.data) {
          head = head.next
          if (head eq this.head) break
          currNode = head.data.next
        }

        list.addOne(currNode.data.get)
        i += 1
        currNode = currNode.next
      }
    }
    var sortList = mergeSort(list)
    this.clean
    for (item <- sortList) this.add(item)
  }


  import java.io.FileOutputStream
  import java.io.ObjectOutputStream

  def save(): Unit = {
    try {
      val oos = new ObjectOutputStream(new FileOutputStream("LinkedList.dat"))
      try {
        var head = this.head.next
        var currNode = head.data.next
        while ( {
          head ne this.head
        }) {
          while ( {
            currNode ne head.data
          }) {
            oos.writeObject(currNode.data)
            currNode = currNode.next
          }
          oos.writeObject(null)
          head = head.next
          currNode = head.data.next
        }
      } catch {
        case ex: Exception =>
          System.out.println(ex.getMessage)
      } finally if (oos != null) oos.close()
    }
  }


  import java.io.FileInputStream
  import java.io.ObjectInputStream

  def load(): Unit = {
    this.clean
    var currNode:Option[V]  = null
    var data:Option[V] = null
    val head = new HeadNode(null, null, null)
    try {
      val ois = new ObjectInputStream(new FileInputStream("LinkedList.dat"))
      try {
        val bool = true
        var check = false
        while ( {
          bool
        }) {
          currNode = ois.readObject.asInstanceOf[Option[V]]
          if (currNode == null) check = true
          else if (check) {
            this.addInNewList(currNode.get)
            check = false
          }
          else {
            this.add(currNode.get)
            data = currNode
          }
          //           		 this.add(currNode);
        }
      } catch {
        case ex: Exception =>
          this.setType(data.get)
      } finally if (ois != null) ois.close()
    }
  }



  def printList: String = {
    var head = this.head.next
    var currNode = head.data.next
    var str = "List 1: "
    var i = 0
    var j = 1
    breakable {
      while ( {
        true
      }) {
        if (currNode eq head.data) {
          j += 1
          head = head.next
          if (head eq this.head) break
          currNode = head.data.next
          str += "  List " + j + ": "
        }
        str += i + ") " + currNode.data + "; "
        i += 1
        currNode = currNode.next
      }
    }
      str

  }



  def printForTest(): Unit = {
    var head = this.head.next
    var currNode = head.data.next
    var i = 0
    var j = 1
    System.out.print("List 1: ")
    breakable {
    while ( {
      true
    }) {
      if (currNode eq head.data) {
        System.out.println()
        j += 1
        head = head.next
        if (head eq this.head) break
        currNode = head.data.next
        System.out.print("List " + j + ": ")
      }
      System.out.print(i + ") " + currNode.data + "; ")
      i += 1
      currNode = currNode.next
    }
  }
  }
}
