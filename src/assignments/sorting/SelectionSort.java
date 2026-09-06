package assignments.sorting;

/**
* SelectionSort<T> repeatedly selects smallest remaining element to put into the correct position.
*
* @param <T> the element type being sorted
*/
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sort an array smallest element at a time using SelectionSort.
     * 
     * Post-condition: array is sorted in ascending order.
     * 
     * SelectionSort is generalized.
     */

    public SelectionSort() {}

    /**    (non-Javadoc)
     * Algorithms scans length of the portion to find the minimum element.
     * @param array an array of comparable elements to be sorted
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Comparable[])
     */
    public void sort(T[] array) {
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

    }

}

//java -cp lib/* -ea src/assignments/sorting/SelectionSort.java
//Quadratic algorithm