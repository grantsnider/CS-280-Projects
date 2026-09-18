package assignments.sorting;

/**
* SelectionSort{@code <T>} repeatedly selects smallest remaining element to put into the correct position.
*
* @param <T> the element type being sorted
*/
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Construct an SelectionSort instance.
     */
    public SelectionSort() {}

    /**
     * Sorts an array using selection sort algorithm.
     * 
     * The algorithm repeatedly scans the unsorted portion of the array to find the minimum element to sort into the next position.
     * 
     * @param array an array of comparable elements to be sorted
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Comparable[])
     */
    public void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0){
                    minIndex = j;
                }
            }
  
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    
    /** 
     * Run validation tests.
     * 
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort<Integer>());
        System.out.println("SelectionSort has passed all tests.");
    }
}
