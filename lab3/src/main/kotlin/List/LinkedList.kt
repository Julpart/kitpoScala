package List

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


internal class Node<V>(var data: V?, var next: Node<V>?, var prev: Node<V>?)

internal class HeadNode<V>(var data: Node<V>, var next: HeadNode<V>?, var prev: HeadNode<V>?){
    var size = 0
}

class LinkedList<V : IUserType?> {
    var comparatorType: Comparator<IUserType>? = null
    private val head = HeadNode<V>(Node<V>(null, null, null), null, null)
    private var currHeadNode = head

    @Transient
    private var size = 0
    private var type: IUserType? = null
    constructor() {
        head.prev = head
        head.next = head.prev
        head.data.prev = head.data
        head.data.next = head.data.prev
    }

    fun copy(): LinkedList<IUserType?> {
        return LinkedList<IUserType?>()
    }

    fun setType(type: IUserType) {
        this.type = type
        comparatorType = type.getTypeComparator()
    }

    fun getType(): IUserType? {
        return type
    }

    fun size(): Int {
        return size
    }

    fun addByIndex(data: V, index: Int): Boolean {
        return if (type != null) {
            if (data!!.getClassName().equals(type!!.getClassName())) {
                addBefore(data, index(index)!!)
                true
            } else false
        } else {
            addBefore(data, index(index)!!)
            true
        }
    }

    private fun addBefore(data: V, node: Node<V>): Node<V>? {
        val new_node: Node<V> = Node<V>(data, node, node.prev)
        new_node.prev?.next = new_node
        new_node.next?.prev = new_node
        size++
        currHeadNode.size++
        return new_node
    }

    fun add(data: V): Boolean {
        return if (type != null) {
            if (data!!.getClassName().equals(type!!.getClassName())) {
                if (currHeadNode == head) {
                    addInNewList(data)
                    return true
                }
                addBefore(data, currHeadNode.data)
                true
            } else false
        } else {
            if (currHeadNode == head) {
                addInNewList(data)
                return true
            }
            addBefore(data, currHeadNode.data)
            true
        }
    }

    fun addInNewList(data: V): Boolean {
        return if (type != null) {
            if (data!!.getClassName().equals(type!!.getClassName())) {
                val HeadNode: HeadNode<V> = HeadNode(Node(null, null, null), head, currHeadNode)
                currHeadNode.next = HeadNode
                currHeadNode = HeadNode
                head.prev = currHeadNode
                currHeadNode.data.prev = currHeadNode.data
                currHeadNode.data.next = currHeadNode.data.prev
                addBefore(data, currHeadNode.data)
                true
            } else false
        } else {
            val HeadNode: HeadNode<V> = HeadNode(Node(null, null, null), head, currHeadNode)
            currHeadNode.next = HeadNode
            currHeadNode = HeadNode
            head.prev = currHeadNode
            currHeadNode.data.prev = currHeadNode.data
            currHeadNode.data.next = currHeadNode.data.prev
            addBefore(data, currHeadNode.data)
            true
        }
    }

    fun printForSort() {
        var head = head.next
        var currNode: Node<V>? = head?.data?.next
        var i = 0
        while (true) {
            if (currNode == head!!.data) {
                head = head.next
                if (head == this.head) break
                currNode = head!!.data.next!!
            }
            println(i.toString() + ")" + currNode?.data)
            i++
            currNode = currNode?.next!!
        }
    }

    private fun index(index: Int): Node<V>? {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index: $index, Size: $size")
        var ind = index
        var headNode = head.next
        var node: Node<V>? = headNode!!.data.next
        while(headNode!!.size <= ind){
            ind = ind - headNode!!.size
            headNode = headNode?.next
            node = headNode?.data?.next
        }
        for (i in 0 until ind) {
            node = node?.next
        }
        return node
    }

    operator fun get(index: Int): V? {
        return index(index)!!.data
    }

    operator fun set(index: Int, data: V): V {
        val node: Node<V>? = index(index)
        val oldVal: V = node?.data!!
        node.data = data
        return oldVal
    }

    private fun removeNode(node: Node<V>): Boolean {
        if (node == currHeadNode.data.next && node == currHeadNode.data.prev) {
            return if (currHeadNode == head) {
                throw NoSuchElementException()
            } else {
                currHeadNode.prev!!.next = currHeadNode.next
                currHeadNode.next!!.prev = currHeadNode.prev
                currHeadNode = currHeadNode.prev!!
                return true
            }
        }
        node.prev?.next = node.next
        node.next?.prev = node.prev
        node.prev = null
        node.next = node.prev
        node.data = null
        size--
        currHeadNode.size--
        return true
    }

    fun remove(): Boolean {
        removeNode(currHeadNode.data.prev!!)
        return true
    }

    fun removeByIndex(index: Int): Boolean {
        removeNode(index(index)!!)
        return true
    }

    fun clean() {
        val size = this.size-1
        for (i in 0..size) {
            removeNode(currHeadNode.data.prev!!)
        }
        type = null
        comparatorType = null
    }

    fun sort() {
        if (size == 0) return
        for (i in size / 2 - 1 downTo 0) {
            heapify(size, i)
        }
        for (i in size - 1 downTo 0) {
            val temp = this[0]
            this.set(0, this.get(i)!!)
            this.set(i, temp!!)
            heapify(i, 0)
        }
    }

    private fun heapify(length: Int, i: Int) {
        val leftChild = 2 * i + 1
        val rightChild = 2 * i + 2
        var largest = i
        if (leftChild < length && comparatorType!!.compare(this.get(leftChild), this.get(largest)) > 0) {
            largest = leftChild
        }
        if (rightChild < length && comparatorType!!.compare(this.get(rightChild), this.get(largest)) > 0) {
            largest = rightChild
        }
        if (largest != i) {
            val temp = this[i]
            this.set(i, this.get(largest)!!)
            this.set(largest, temp!!)
            heapify(length, largest)
        }

    }

    fun save() {
        try {
            ObjectOutputStream(FileOutputStream("LinkedList.dat")).use { oos ->
                var head = head.next
                var currNode: Node<V>? = head!!.data.next
                while (head != this.head) {
                    while (currNode != head!!.data) {
                        oos.writeObject(currNode?.data)
                        currNode = currNode?.next
                    }
                    oos.writeObject(null)
                    head = head.next
                    currNode = head!!.data.next
                }
            }
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun load() {
        clean()
        var currNode: V? = null
        var data: V? = null
        val head: HeadNode<V> = HeadNode(Node<V>(null, null, null), null, null)
        try {
            ObjectInputStream(FileInputStream("LinkedList.dat")).use { ois ->
                val bool = true
                var check = false
                while (bool) {
                    currNode = ois.readObject() as V
                    if (currNode == null) {
                        check = true
                    } else {
                        if (check) {
                            addInNewList(currNode!!)
                            check = false
                        } else {
                            add(currNode!!)
                            data = currNode
                        }
                    }
                }
            }
        } catch (ex: java.lang.Exception) {
            setType(data!!)
        }
    }
    fun printList(): String? {
        var head = head.next
        var currNode: Node<V>? = head!!.data.next
        var str = "List 1: "
        var i = 0
        var j = 1
        while (true) {
            if (currNode == head!!.data) {
                j++
                head = head.next
                if (head == this.head) break
                currNode = head!!.data.next
                str += "  List $j: "
            }
            str += i.toString() + ") " + currNode?.data + "; "
            i++
            currNode = currNode?.next
        }
        return str
    }

    fun printForTest() {
        var head = head.next
        var currNode: Node<V>? = head!!.data.next
        var i = 0
        var j = 1
        print("List 1: ")
        while (true) {
            if (currNode == head!!.data) {
                println()
                j++
                head = head.next
                if (head == this.head) break
                currNode = head!!.data.next
                print("List $j: ")
            }
            print(i.toString() + ") " + currNode!!.data + "; ")
            i++
            currNode = currNode!!.next
        }
    }

}