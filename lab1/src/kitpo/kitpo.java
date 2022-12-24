package kitpo;

import javax.swing.JFrame;

import View.View;
import objects.MyDot;
import objects.MyVector;


public class kitpo {

    public static void main(String[] args) {
		 /*
		LinkedListFactory factory = new LinkedListFactory();
		IUserType dot1 = new MyDot(1,1);
		IUserType dot2 = new MyDot(0,0);
		IUserType dot3 = new MyDot(-1,2);
		LinkedList list = factory.getBuilderByName(dot1.getClassName());
		list.add(dot1);
		list.add(dot2);
		list.add(dot3);
		list.printList();
		list.forEach((a)->{  System.out.println(a); });
		list.sort();
		list.remove();
		list.printList();
		list.save();
		list.load();
		list.printList();
		list.exportToXML();
		*/


		 /*
			MyVector dot1 = new MyVector(-10,12,7,3);
			System.out.println(dot1.getLength());
	              View w = new View();
	      	      w.setVisible(true);
	      */

		 /*
		 LinkedListFactory factory = new LinkedListFactory();
		 IUserType dot1 = new MyDot(1,1);
		 LinkedList list = factory.getBuilderByName(dot1.getClassName());
		 for(int i = 0;i<1000000;i++) {
			 list.add(new MyDot());
		 }
		 list.printForSort();
		 list.sort();
		 list.printForSort();

		 */
        LinkedListFactory factory = new LinkedListFactory();
        LinkedList list = factory.getBuilderByName(new MyDot().getClassName());
        LinkedList list2 = factory.getBuilderByName(new MyDot().getClassName());
        for(int i = 0;i<1000;i++) {
                list.add(new MyDot());
                list2.add(new MyDot());
        }
        long start = System.nanoTime();
        list.sort();
        long end = System.nanoTime();
        System.out.println((end - start) * 1.0 / 1000000);
        long start2 = System.nanoTime();
        list2.sort();
        long end2 = System.nanoTime();
        System.out.println((end2 - start2) * 1.0 / 1000000);

    }
}

