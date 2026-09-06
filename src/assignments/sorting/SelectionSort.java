package assignments.sorting;

/**
*Putting out the smallest element one-by-one.
*
*@param <T> the element type being sorted
*/
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sort an array smallest element at a time using SelectionSort.
     * 
     * Post-condition: array is sorted in ascending order.
     * 
     * @param array an array of integers.
     * 
     * SelectionSort is generalized.
     */

    public SelectionSort() {}
    public void sort(T[] array) {
        /**
         * Algorithm scans length of the portion to find minimum element.
         */
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                /**
                 * If element found, it swaps to current index
                 */
                if (array[j].compareTo(array[minIndex]) < 0){
                    minIndex = j;
                }
                /**
                 * That element is then compared.
                 */
            }
            /**
             * When i passes, they're then sorted into position.
             */
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }

    }
    
    /** 
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort<Integer>());
        System.out.println("SelectionSort has passed all tests.");

        // Fill an array with random numbers.
        int N = 4812;
        Integer[] array = new Integer[N];
        for (int i =0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new SelectionSort<Integer>();
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);

    }

}

//java -cp lib/* -ea src/assignments/sorting/SelectionSort.java
//Quadratic algorithm