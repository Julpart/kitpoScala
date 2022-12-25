package test;
import kitpo.LinkedList;
import kitpo.LinkedListFactory;
import objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
public class TestLinkedList {
    private LinkedListFactory factory;
    private LinkedList actualList, expectedList;

    @BeforeEach
    public void setUp() {
        factory = new LinkedListFactory();
        actualList = factory.getBuilderByName(new MyDot().getClassName());
        expectedList = factory.getBuilderByName(new MyDot().getClassName());
    }

    @Test
    public void testSortEqualElements() {
        var dot = new MyDot(1,1);
        for (int i =0;i<10;i++) {
            actualList.add(dot);
            expectedList.add(dot);
        }

        actualList.sort();

        assertEquals(expectedList.toString(),actualList.toString());
    }

    @Test
    public void testSortOrdElements() {
        MyDot[] list = new MyDot[] {new MyDot(0,0),new MyDot(1,1),new MyDot(2,2),new MyDot(3,3),
                new MyDot(4,4),new MyDot(5,5),new MyDot(6,6),new MyDot(7,7),new MyDot(8,8),new MyDot(9,9)};
        for (MyDot el:list
        ) {
            actualList.add(el);
        }
        for (MyDot el:list
        ) {
            expectedList.add(el);
        }
        actualList.sort();

        assertEquals(expectedList.toString(),actualList.toString());
    }

    @Test
    public void testSortRevordElements() {
        MyDot[] list = new MyDot[] {new MyDot(0,0),new MyDot(1,1),new MyDot(2,2),new MyDot(3,3),
                new MyDot(4,4),new MyDot(5,5),new MyDot(6,6),new MyDot(7,7),new MyDot(8,8),new MyDot(9,9)};

        for (int i = list.length-1; i >= 0; i--) {
            actualList.add(list[i]);
        }
        for (MyDot el : list
        ) {
            expectedList.add(el);
        }

        actualList.sort();

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testSortRepeatedElements() {
        ArrayList<MyDot> list = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(1,1),
                new MyDot(2,2), new MyDot(3,3), new MyDot(4,4), new MyDot(5,5)));

        for (MyDot el : list) {
            expectedList.add(el);
        }
        for (int i=0;i<list.size();) {
            var item = (int)(Math.random() * list.size());
            actualList.add(list.get(item));
            list.remove(item);
        }

        actualList.sort();
        assertEquals(expectedList.toString(), actualList.toString());
    }


    @Test
    public void testSortRepeatedGroups() {
        ArrayList<MyDot> list = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(1,1),
                new MyDot(2,2), new MyDot(3,3), new MyDot(3,3), new MyDot(4,4)));

        for (MyDot el : list) {
            expectedList.add(el);
        }
        for (int i=0;i<list.size();) {
            var item = (int)(Math.random() * list.size());
            actualList.add(list.get(item));
            list.remove(item);
        }

        actualList.sort();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testSortExtremumInCenter() {
        ArrayList<MyDot> testList = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(2,2),
                new MyDot(3,3), new MyDot(9999,9999), new MyDot(4,4), new MyDot(5,5), new MyDot(6,6)));
        ArrayList<MyDot> sortList = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(2,2),
                new MyDot(3,3), new MyDot(4,4), new MyDot(5,5), new MyDot(6,6), new MyDot(9999,9999)));

        for (MyDot el : sortList) {
            expectedList.add(el);
        }
        for (MyDot el : testList) {
            actualList.add(el);
        }

        actualList.sort();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testSortExtremeInBeginning() {
        ArrayList<MyDot> testList = new ArrayList<MyDot>(Arrays.asList(new MyDot(9999,9999),new MyDot(1,1),
                new MyDot(2,2), new MyDot(3,3), new MyDot(4,4), new MyDot(5,5), new MyDot(6,6)));
        ArrayList<MyDot> sortList = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(2,2),
                new MyDot(3,3), new MyDot(4,4), new MyDot(5,5), new MyDot(6,6), new MyDot(9999,9999)));

        for (MyDot el : sortList) {
            expectedList.add(el);
        }
        for (MyDot el : testList) {
            actualList.add(el);
        }

        actualList.sort();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testSortExtremeInEnding() {
        ArrayList<MyDot> testList = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),
                new MyDot(2,2), new MyDot(3,3), new MyDot(5,5), new MyDot(4,4), new MyDot(6,6),new MyDot(9999,9999)));
        ArrayList<MyDot> sortList = new ArrayList<MyDot>(Arrays.asList(new MyDot(1,1),new MyDot(2,2),
                new MyDot(3,3), new MyDot(4,4), new MyDot(5,5), new MyDot(6,6), new MyDot(9999,9999)));

        for (MyDot el : sortList) {
            expectedList.add(el);
        }
        for (MyDot el : testList) {
            actualList.add(el);
        }

        actualList.sort();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testSortEfficiency() {
        for (double i = 0.5; i <= 4000; i *= 2) {
            int n = (int)(i * 1000);
            System.out.print(n + ": ");
            var linkedList = factory.getBuilderByName(new MyDot().getClassName());
            for (int j = 0; j < n; j++) linkedList.add(new MyDot());

            long start = System.nanoTime();
                linkedList.sort();
            long end = System.nanoTime();
            System.out.println((end - start) * 1.0 / 1000000);
        }
    }

    @Test
    public void testSetEfficiency() {
        for (int k = 1; k <= 6;k+=2) {
            int size = k * 100000;
            var linkedList = factory.getBuilderByName(new MyDot().getClassName());
            for (int j = 0; j < size; j++) linkedList.add(new MyDot());
            System.out.println("Размер списка: " + size);
            linkedList.balance();
            for (double i = 0.5; i <= 512; i *= 2) {
                int n = (int) (i * 1000);
                System.out.println("Количество вставок: " + n);
                long start = System.nanoTime();
                for (int j = 0; j < n; j++) {
                    linkedList.set(size/2,new MyDot());
                }
                long end = System.nanoTime();
                System.out.println("Новая: " + (end - start) * 1.0 / 1000000);

                long starto = System.nanoTime();
                for (int j = 0; j < n; j++) {
                    linkedList.oldSet(size/2,new MyDot());
                }
                long endo = System.nanoTime();
                System.out.println("Старая: " + (endo - starto) * 1.0 / 1000000);
            }




        }

    }
    @Test
    public void testSetEfficiency2() {
        for (double k = 0.5; k <= 512;k*=2) {
            int size =(int) (k * 1000);
            var linkedList = factory.getBuilderByName(new MyDot().getClassName());
            for (int j = 0; j < size; j++) linkedList.add(new MyDot());
            System.out.println("Размер списка: " + size);
            linkedList.balance();

                long start = System.nanoTime();
                for (int j = 0; j < 100000; j++) {
                    linkedList.set(size/2,new MyDot());
                }
                long end = System.nanoTime();
                System.out.println("Новая: " + (end - start) * 1.0 / 1000000);

                long starto = System.nanoTime();
                for (int j = 0; j < 100000; j++) {
                    linkedList.oldSet(size/2,new MyDot());
                }
                long endo = System.nanoTime();
                System.out.println("Старая: " + (endo - starto) * 1.0 / 1000000);





        }

    }

}