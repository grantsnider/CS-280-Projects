package assignments.sorting;

import assignments.datastructures.MinHeap;

/**
 * HeapSort enqueues all the items into a minHeap, thne dequeues all the items from the heap.
 */
public class HeapSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Sorts the array using MinHeap.
     * @param array the array values being sorted.
     */
    public void sort(T[] array) {
        MinHeap<T> heap = new MinHeap<>();
        for (T value : array) {
            heap.enqueue(value);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.dequeue();
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args.
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new HeapSort<Integer>());

        System.out.println("HeapSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/HeapSort.java