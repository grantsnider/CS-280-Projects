package assignments.algorithms;

import assignments.datastructures.Vector;
import assignments.datastructures.LinkedList;
import assignments.datastructures.CircularLinkedList;

/**
 * Performs an empirical runtime analysis of prepending operations.
 * Such as Vector, LinkedList, and CircularLinkedList.
 */
public class ERAPrepending {

    /**
     * Measures the runtime of prepending to a Vector.
     */
    public static void testVector() {
        System.out.println("Vector");
        System.out.println("N\tTime");
        for (int n = 100; n <= 10000000; n *= 10) {
            Vector<Integer> vector = new Vector<>();
            for (int i = 0; i < n; i++) {
                vector.insert(vector.length(), 0);
            }

            long start = System.nanoTime();
            vector.insert(0, 0);
            long end = System.nanoTime();
            double time = (end - start) / 1e9;
            System.out.println(n + "\t" + time);
        }
    }

    /**
     * Measures the runtime of prepending to an LinkedList.
     */
    public static void testLinkedList() {
        System.out.println("LinkedList");
        System.out.println("N\tTime");
        for (int n = 100; n <= 1000000; n *= 10) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                linkedList.insert(0, 0);
            }

            long start = System.nanoTime();
            linkedList.insert(0, 0);
            long end = System.nanoTime();
            double time = (end - start) / 1e9;
            System.out.println(n + "\t" + time);
        }
    }

    /**
     * Measures the prepending time of an CircularLinkedList.
     */
    public static void testCircularLinkedList() {
        System.out.println("CircularLinkedList");
        System.out.println("N\tTime");
        for (int n = 100; n <= 1000000; n *= 10) {
            CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
            for (int i = 0; i < n; i++) {
                circularLinkedList.insert(0, 0);
            }

            long start = System.nanoTime();
            circularLinkedList.insert(0, 0);
            long end = System.nanoTime();
            double time = (end - start) / 1e9;
            System.out.println(n + "\t" + time);
        }
    }

    /**
     * Runs all the prepending timing operations.
     * 
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        testVector();
        testLinkedList();
        testCircularLinkedList();
    }
}

//java -cp lib/* -ea src/assignments/algorithms/ERAPrepending.java