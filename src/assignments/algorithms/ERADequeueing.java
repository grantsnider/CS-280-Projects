package assignments.algorithms;

import assignments.datastructures.MinHeap;
import assignments.datastructures.PriorityDequeuer;
import assignments.datastructures.PriorityEnqueuer;

/**
 * ERADequeueing measures the runtime to dequeue ALL values from multiple queue implementations.
 * It tests PriorityEnqueuer, PriorityDequeuer, and MinHeap.
 * Each data point is filled with random values and the preperation time isn't included in the runtime.
 */
public class ERADequeueing {
    /**
     * Measures the time to dequeue all values from MinHeap with n values.
     * @param n the number of values.
     * @return the time in nanoseconds.
     */
    private static long timeMinHeap(int n) {
        MinHeap<Integer> heap = new MinHeap<>();
        for (int i = 0; i < n; i++) {
            heap.enqueue((int)(Math.random() * n + 1));
        }
        long begin = System.nanoTime();
        while (!heap.isEmpty()) {
            heap.dequeue();
        }
        return System.nanoTime() - begin;
    }
    /**
     * Measures the time to dequeue all values from PriorityEnqueuer with n values.
     * @param n the number of values.
     * @return the time in nanoseconds.
     */
    private static long timePriorityEnqueuer(int n) {
        PriorityEnqueuer<Integer> enqueuer = new PriorityEnqueuer<>();
        for (int i = 0; i < n; i++) {
            enqueuer.enqueue((int)(Math.random() * n + 1));
        }
        long begin = System.nanoTime();
        while (!enqueuer.isEmpty()) {
            enqueuer.dequeue();
        }
        return System.nanoTime() - begin;
    }
    /**
     * Measures the time to dequeue all values from PriorityDequeuer with n values.
     * @param n the number of values.
     * @return the time in nanoseconds.
     */
    private static long timePriorityDequeuer(int n) {
        PriorityDequeuer<Integer> dequeuer = new PriorityDequeuer<>();
        for (int i = 0; i < n; i++) {
            dequeuer.enqueue((int)(Math.random() * n + 1));
        }
        long begin = System.nanoTime();
        while (!dequeuer.isEmpty()) {
            dequeuer.dequeue();
        }
        return System.nanoTime() - begin;
    }
    /**
     * Generates the runtime for several queue implementations.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("N, PriorityEnqueuer, PriorityDequeuer, MinHeap");
        for (int n = 100; n <= 12800000; n *= 2) {
            long enqueuer = timePriorityEnqueuer(n);
            long dequeuer = timePriorityDequeuer(n);
            long heap = timeMinHeap(n);
            System.out.println(n + ", " + enqueuer + ", " + dequeuer + ", " + heap);
        }
    }
}

//java -cp lib/* -ea src/assignments/algorithms/ERADequeueing.java