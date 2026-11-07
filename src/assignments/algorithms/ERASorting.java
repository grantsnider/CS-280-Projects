package assignments.algorithms;

import assignments.sorting.BubbleSort;
import assignments.sorting.InsertionSort;
import assignments.sorting.SelectionSort;
import assignments.sorting.QuickSort;
import assignments.sorting.MergeSort;
import assignments.sorting.TreeSort;
import assignments.sorting.HeapSort;
import assignments.sorting.BucketSort;

/**
 * ERASorting measures how long the runtime takes to sort each array of N elements.
 * It tests Bubble, Insertion, Selection, Quick, Merge, Tree, Heap, and Bucket Sorts.
 * Each array has fresh data poinntsm and doesn't include the time to prepare the array in the runtime.
 * It initializes the array using N random numbers from 0 to N-1.
 */
public class ERASorting {
    /**
    * Creates the random Integer array.
    * @param n the array size.
    * @return the random array.
    */
    private static Integer[] randomArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int)(Math.random() * n);
        }
        return array;
    }

    /**
    * Measures the BubbleSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeBubbleSort(int n) {
        Integer[] array = randomArray(n);
        BubbleSort<Integer> bubble = new BubbleSort<>();
        long begin = System.nanoTime();
        bubble.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the InsertionSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeInsertionSort(int n) {
        Integer[] array = randomArray(n);
        InsertionSort<Integer> insert = new InsertionSort<>();
        long begin = System.nanoTime();
        insert.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the SelectionSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeSelectionSort(int n) {
        Integer[] array = randomArray(n);
        SelectionSort<Integer> select = new SelectionSort<>();
        long begin = System.nanoTime();
        select.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the QuickSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeQuickSort(int n) {
        Integer[] array = randomArray(n);
        QuickSort<Integer> quick = new QuickSort<>();
        long begin = System.nanoTime();
        quick.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the MergeSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeMergeSort(int n) {
        Integer[] array = randomArray(n);
        MergeSort<Integer> merge = new MergeSort<>();
        long begin = System.nanoTime();
        merge.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the TreeSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeTreeSort(int n) {
        Integer[] array = randomArray(n);
        TreeSort<Integer> tree = new TreeSort<>();
        long begin = System.nanoTime();
        tree.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Measures the HeapSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeHeapSort(int n) {
        Integer[] array = randomArray(n);
        HeapSort<Integer> heap = new HeapSort<>();
        long begin = System.nanoTime();
        heap.sort(array);
        return System.nanoTime() - begin;
    }
    
    /**
    * Measures the BucketSort runtime.
    * @param n the array size.
    * @return the runtime in nanoseconds.
    */
    private static long timeBucketSort(int n) {
        Integer[] array = randomArray(n);
        BucketSort bucket = new BucketSort();
        long begin = System.nanoTime();
        bucket.sort(array);
        return System.nanoTime() - begin;
    }

    /**
    * Generates the runtime for multiple sorting algorithms.
    * @param args command-line arguments.
    */
    public static void main(String[] args) {
        System.out.println("N, Quick, Merge, Tree, Heap, Bucket");
        for (int n = 100; n <= 3276800; n *= 2) {
            System.out.println(n 
                + "," + timeQuickSort(n) + "," + timeMergeSort(n) + "," + timeTreeSort(n) 
                + "," + timeHeapSort(n) + "," + timeBucketSort(n)
            );
        }
        System.out.println("N, Bubble, Insertion, Selection");
        for (int n = 100; n <= 409600; n*= 2) {
            System.out.println(n + "," + timeBubbleSort(n) + "," + timeInsertionSort(n) + "," + timeSelectionSort(n));
        }
    }
}

//java -cp lib/* -ea src/assignments/algorithms/ERASorting.java