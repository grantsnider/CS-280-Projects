package assignments.datastructures;

import java.util.Vector;

import adt.PriorityQueue;
/**
 * MinHeap implements the
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    /**
     * Initilize an empty MinHeap.
     */
    public MinHeap() {
        heap = new Vector<>();
    }
    /**
     * Determines whether the heap has values.
     *@return true if the heap is empty.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    /**
     * Views the minimum value without removing it from the heap.
     * The runtime is always O(1).
     * @return the smallest value.
     */
    public T peek() {
        assert !isEmpty();
        return heap.get(0);
    }
    /**
     * Inserts an value into the heap.
     * The operation executes at O(log n).
     * @param value the value being added.
     */
    public void enqueue(T value) {
        heap.add(value);
        int child = heap.size() - 1;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (heap.get(child).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            T temp = heap.get(child);
            heap.set(child, heap.get(parent));
            heap.set(parent, temp);
            child = parent;
        }
    }
    /**
     * Remove and returns the minimum value from heap.
     * This method runs at O(log n).
     * @return returning the deleted value.
     */
    public T dequeue() {
        assert !isEmpty();
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (heap.isEmpty()) {
            return min;
        }
        heap.set(0, last);
        int parent = 0;
        while(true) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int small = parent;
            if (left < heap.size() && heap.get(left).compareTo(heap.get(small)) < 0) {
                small = left;
            }
            if (right < heap.size() && heap.get(right).compareTo(heap.get(small)) < 0) {
                small = right;
            }
            if (small == parent) {
                break;
            }
            T temp = heap.get(parent);
            heap.set(parent, heap.get(small));
            heap.set(small, temp);
            parent = small;
        }

        return min;
    }
    /**
     * Storage for the binary search tree heap.
     */
    private Vector<T> heap;
    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        PriorityQueue.validate(new MinHeap<>());

        // Method tests.
        MinHeap<Integer> heap = new MinHeap<>();
        assert heap.isEmpty();
        heap.enqueue(6); heap.enqueue(3); heap.enqueue(12);
        assert heap.peek() == 3;
        assert heap.dequeue() == 3;
        assert heap.peek() == 6;
        assert heap.dequeue() == 6; assert heap.dequeue() == 12;
        assert heap.isEmpty();

        System.out.println("MinHeap passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/MinHeap.java