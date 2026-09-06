package assignments.sorting;

/**
*Putting elements one-by-one into a gauranteed-sorted list.
*/
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sort an array into two groups using InsertionSort.
     * 
     * Post-condition: array is sorted in ascending order.
     * 
     * @param array an array of integers.
     * 
     * InsertionSort is generalized.
     */

    public InsertionSort() {}
    /**
     * 
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Object[])
     * Keeps the left side to be sorted.
     * Each element shifts larger ones to the right until its correctly placed.
     */
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T Key = array[i];
            int j = i - 1;


            while (j>= 0 && array[j].compareTo(Key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = Key;

        }

    }

    /** 
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<Integer>());
        System.out.println("InsertionSort has passed all tests.");

        // Fill an array with random numbers.
        int N = 4812;
        Integer[] array = new Integer[N];
        for (int i =0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new InsertionSort<Integer>();
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);

    }
}

//java -cp lib/* -ea src/assignments/sorting/InsertionSort.java
//Quadratic algorithm