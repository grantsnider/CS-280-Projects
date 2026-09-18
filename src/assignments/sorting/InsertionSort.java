package assignments.sorting;

/**
* InsertionSort{@code <T>} builds a sorted position of the array one element at a time.
*
* @param <T> the element type being sorted
*/
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Construct an InsertionSort instance.
     */
    public InsertionSort() {}

    /**
     * The left portion of the array is maintained in sorted order. Each element is insrted into the proper position by shifting the larger element to the right.
     * 
     * @param array an array of comparable elements to be sorted.
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Comparable[])
     */
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            while (j>= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /** 
     * Run validation tests.
     * 
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<Integer>());
        System.out.println("InsertionSort has passed all tests.");
    }
}