package assignments.sorting;

/**
* InsertionSort{@code <T>} builds a sorted position of the array one element at a time.
*
* @param <T> the element type being sorted
*/
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public InsertionSort() {}
    /**
     * 
     * Keeps the left side to be sorted.
     * Each element shifts larger ones to the right until its correctly placed.
     * 
     * @param array an array of comparable elements to be sorted.
     * @see assignments.sorting.SortingAlgorithm#sort(java.lang.Object[])
     * 
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

    }
}

//java -cp lib/* -ea src/assignments/sorting/InsertionSort.java
//Quadratic algorithm