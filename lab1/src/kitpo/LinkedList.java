package kitpo;

import objects.MyDot;

import java.util.*;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;




class Node<V> {
    V data;
    Node<V> next;
    Node<V> prev;

    Node (V data,Node<V> next,Node<V> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;

    }
}

class HeadNode<V> {
    HeadNode<V> next;
    Node<V> data;
    HeadNode<V> prev;

    int size=0;

    HeadNode (Node<V> data,HeadNode<V> next,HeadNode<V> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;

    }
}

interface Consumer<V> {
    void accept(V data);

}
public class LinkedList<V extends IUserType> {
    Comparator comparatorType;


    public void forEach(Consumer<V> action) {
        Objects.requireNonNull(action);
        Node<V> currNode = this.head.data.next;
        while (currNode != this.head.data) {
            action.accept(currNode.data);
            currNode = currNode.next;
        }
    }

    private HeadNode<V> head = new HeadNode<V>(new Node<V>(null,null,null),null,null);
    private HeadNode<V> currHeadNode = head;
    private transient int size = 0;
    private IUserType type = null;
    public LinkedList() {
        head.next = head.prev = head;
        head.data.next = head.data.prev = head.data;
    }

    public LinkedList<IUserType> copy() {
        return new LinkedList<IUserType>();
    }

    public void setType(IUserType type) {
        this.type = type;
        this.comparatorType = type.getTypeComparator();
    }

    public IUserType getType() {
        return this.type;
    }

    public int size() {
        return size;
    }

    public boolean add(V data) {
        if(type != null) {
            if(data.getClassName().equals(type.getClassName())) {
                if(this.currHeadNode == this.head) {
                    addInNewList(data);
                    return true;
                }
                addBefore(data, this.currHeadNode.data);
                return true;
            }else return false;

        }else {
            if(this.currHeadNode == this.head) {
                addInNewList(data);
                return true;
            }
            addBefore(data, this.currHeadNode.data);
            return true;
        }
    }

    public boolean addInNewList(V data) {
        if(type != null) {
            if(data.getClassName().equals(type.getClassName())) {
                HeadNode<V> HeadNode = new HeadNode(new Node(null,null,null),head,currHeadNode);
                this.currHeadNode.next = HeadNode;
                this.currHeadNode = HeadNode;
                this.head.prev = this.currHeadNode;
                this.currHeadNode.data.next = this.currHeadNode.data.prev = this.currHeadNode.data;
                addBefore(data, this.currHeadNode.data);
                return true;
            }else return false;

        }else {
            HeadNode<V> HeadNode = new HeadNode(new Node(null,null,null),head,currHeadNode);
            this.currHeadNode.next = HeadNode;
            this.currHeadNode = HeadNode;
            this.head.prev = this.currHeadNode;
            this.currHeadNode.data.next = this.currHeadNode.data.prev = this.currHeadNode.data;
            addBefore(data, this.currHeadNode.data);
            return true;
        }
    }

    public boolean addByIndex(V data,int index) {
        if(type != null) {
            if(data.getClassName().equals(type.getClassName())) {
                addBefore(data, index(index));
                return true;
            }else return false;

        }else {
            addBefore(data, index(index));
            return true;
        }
    }

    public boolean remove() {
        remove(currHeadNode.data.prev);
        return true;
    }

    public boolean removeByIndex(int index) {
        remove(index(index));
        return true;
    }

    public void clean() {

        for(int i=0;i!=this.size;) {
            remove(currHeadNode.data.prev);
        }
        this.type = null;
        this.comparatorType = null;

    }

    public V get(int index) {
        return index(index).data;
    }

    public V set(int index, V data) {
        Node<V> node = index(index);
        V oldVal = node.data;
        node.data = data;
        return oldVal;
    }

    private Node<V> addBefore(V data,Node<V> node) {
        Node<V> new_node = new Node<V>(data,node,node.prev);
        new_node.prev.next = new_node;
        new_node.next.prev = new_node;
        size++;
        currHeadNode.size++;
        return new_node;
    }

    private boolean remove(Node<V> node){
        if (node == currHeadNode.data.next && node == currHeadNode.data.prev) {
            if(currHeadNode == head) {
                throw new NoSuchElementException();
            }else {
                currHeadNode.prev.next = currHeadNode.next;
                currHeadNode.next.prev = currHeadNode.prev;
                currHeadNode = currHeadNode.prev;
                size--;
                return true;
            }
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
        node.data = null;
        size--;
        currHeadNode.size--;
        return true;

    }


    private Node<V> index(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        var ind = index;
        HeadNode<V> headNode = head.next;
        Node<V> node = headNode.data.next;
        while(headNode.size <= ind){
            ind = ind - headNode.size;
            headNode = headNode.next;
            node = headNode.data.next;
        }
        for (int i = 0;i<ind;i++) {
            node = node.next;
        }

        return node;
    }

    public String printList() {
        HeadNode<V> head = this.head.next;
        Node<V> currNode = head.data.next;
        String str = "List 1: ";
        int i = 0;
        int j = 1;
        while (true) {
            if(currNode == head.data) {
                j++;
                head = head.next;
                if(head == this.head) break;
                currNode = head.data.next;
                str += "  List " + j +": ";
            }
            str += i +") " + currNode.data + "; ";
            i++;
            currNode = currNode.next;
        }
        return str;
    }
    @Override
    public String toString(){
        HeadNode<V> head = this.head.next;
        Node<V> currNode = head.data.next;
        String str = "List 1: ";
        int i = 0;
        int j = 1;
        while (true) {
            if(currNode == head.data) {
                j++;
                head = head.next;
                if(head == this.head) break;
                currNode = head.data.next;
                str += "  List " + j +": ";
            }
            str += i +") " + currNode.data + "; ";
            i++;
            currNode = currNode.next;
        }
        return str;
    }
    public void printForSort() {
        HeadNode<V> head = this.head.next;
        Node<V> currNode = head.data.next;
        int i = 0;
        while (true) {
            if(currNode == head.data) {
                head = head.next;
                if(head == this.head) break;
                currNode = head.data.next;
            }
            System.out.println(i+ ")" + currNode.data);
            i++;
            currNode = currNode.next;
        }
    }

    public void printForTest() {
        HeadNode<V> head = this.head.next;
        Node<V> currNode = head.data.next;
        int i = 0;
        int j = 1;
        System.out.print("List 1: ");
        while (true) {
            if(currNode == head.data) {
                System.out.println();
                j++;
                head = head.next;
                if(head == this.head) break;
                currNode = head.data.next;
                System.out.print("List " + j + ": ");
            }
            System.out.print(i +") " + currNode.data + "; ");
            i++;
            currNode = currNode.next;
        }
    }

    private void heapify(int length, int i) {
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largest = i;

        if (leftChild < length && this.comparatorType.compare(this.get(leftChild),this.get(largest)) > 0 ) {
            largest = leftChild;
        }

        if (rightChild < length && this.comparatorType.compare(this.get(rightChild),this.get(largest)) > 0) {
            largest = rightChild;
        }
        if (largest != i) {
            V temp = this.get(i);
            this.set(i, this.get(largest));
            this.set(largest, temp);
            heapify(length, largest);
        }

    }

    public void oldsort() {
        if (this.size == 0) return;

        for (int i = this.size / 2-1; i >= 0; i--) {
            heapify(this.size, i);
        }
        for (int i = this.size-1; i >= 0; i--) {
            V temp = this.get(0);
            this.set(0, this.get(i));
            this.set(i, temp);

            heapify(i, 0);
        }
    }

    private List<V> mergeSort(List<V> list){
        if(list.size() <= 1){
            return list;
        }else{
            var sortedList = new ArrayList<V>();
            var leftList = list.subList(0,list.size()/2);
            var rightList = list.subList(list.size()/2,list.size());

             leftList = mergeSort(leftList);
             rightList = mergeSort(rightList);

            var i = 0;
            var j = 0;
            while (i < leftList.size() && j < rightList.size()) {
                if (this.comparatorType.compare(leftList.get(i),rightList.get(j)) < 0) {
                    sortedList.add(leftList.get(i));
                    i += 1;
                }
                else {
           //         sortedList.add(rightList.get(j));
                    j += 1;
                }
            }
            while (i < leftList.size()) {
                sortedList.add(leftList.get(i));
                i += 1;
            }
            while (j < rightList.size()) {
         //       sortedList.add(rightList.get(j));
                j += 1;
            }
            return sortedList;
        }
    }

    public void sort() {
        var list = new ArrayList<V>();
        var head = this.head.next;
        var currNode = head.data.next;
        var i = 0;
        while (true) {
                if (currNode == head.data) {
                    head = head.next;
                    if (head == this.head) break;
                    currNode = head.data.next;
                }

                list.add(currNode.data);
                i += 1;
                currNode = currNode.next;
            }

        var sortList = mergeSort(list);
        this.clean();
        for (V el:sortList) {
            this.add(el);
        }
    }

    public void save() {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LinkedList.dat")))
        {
            HeadNode<V> head = this.head.next;
            Node<V> currNode = head.data.next;
            while(head != this.head) {
                while (currNode != head.data) {
                    oos.writeObject(currNode.data);
                    currNode = currNode.next;
                }
                oos.writeObject(null);
                head = head.next;
                currNode = head.data.next;
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public void balance() {
        var list = new ArrayList<V>();
        var divisors = new ArrayList<Integer>();
        var head = this.head.next;
        var currNode = head.data.next;
        int n;
        while (true) {
            if (currNode == head.data) {
                head = head.next;
                if (head == this.head) break;
                currNode = head.data.next;
            }

            list.add(currNode.data);
            currNode = currNode.next;
        }
        this.clean();
        for (int i = 1; i <= list.size(); i++) {
            if (list.size() % i == 0) {
                divisors.add(i);
            }
        }

            n = divisors.get((int) (divisors.size() / 2));
        this.setType(list.get(0));
        for (int i = 0; i < list.size(); i++) {
            if (i % n == 0) {
                this.addInNewList(list.get(i));
            } else {
                this.add(list.get(i));
            }
        }
    }

    public void load() {
        this.clean();
        V currNode = null;
        V data = null;
        HeadNode head = new HeadNode(null,null,null);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LinkedList.dat")))
        {

            boolean bool = true;
            boolean check = false;
            while(bool) {
                currNode = (V) ois.readObject();
                if(currNode == null) {
                    check = true;
                } else {
                    if(check) {
                        this.addInNewList(currNode);
                        check = false;
                    }else {
                        this.add(currNode);
                        data = currNode;
                    }
                }

                //           		 this.add(currNode);
            }
        }
        catch(Exception ex){
            this.setType(data);
        }
    }

    public void exportToXML()  {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("data.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(this);
            encoder.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }


}