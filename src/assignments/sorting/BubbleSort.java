package assignments.sorting;

/**
 * BubbleSort<T> repeatedly comapres adjacent elements and swaps them when they are out of order.
 * 
 * @param <T> the element type being sorted
 */
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sort an array in-place using bubble sort.
     * 
     
     * Post-condition: array is sorted in ascending order
     * 
     * Fixed the public section below to now incorporate the generalized SortingAlgorithm.
     */
    public BubbleSort() {}

    /**    (non-Javadoc)
     * @param array the array to sort
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Comparable[])
     */

    public void sort(T[] array) {
        for (int k = array.length; k >= 2; k --) {   // k is the length of the sub-array we are looping through in the inner loop
            for (int i = 0; i < k-1; i ++) { // go up to k-1 because we don't need to comapre the last index against its neighbor
                if (array[i].compareTo(array[i+1]) > 0) {
                    // swap adjacent items (i and i+1)
                    swap(array, i, i+1);
                }
            }
        }
    }

    /**
     * Swap two elements within an array
     * @param array the array to swap values in
     * @param i the first index to swap
     * @param j the second index to swap
     */

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /** 
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BubbleSort<Integer>());
        System.out.println("BubbleSort has passed all tests.");

        // Fill an array with random numbers.
        int N = 4812;
        Integer[] array = new Integer[N];
        for (int i =0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new BubbleSort<Integer>();
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);

    }
    
}

//java -cp lib/* -ea src/assignments/sorting/BubbleSort.java
//Quadratic algorithm