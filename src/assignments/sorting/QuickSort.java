package assignments.sorting;

/**
 * Quicksort sorts values relative to a pivot.
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Intialize the QuickSort.
     */
    public QuickSort() {}

    /**
     * Sorts the array.
     * @param array the array to sort.
     */
    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursively sorts the array.
     * @param array the array to sort.
     * @param low lowest index.
     * @param high highest index.
     */
    private void quickSort(T[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(array, low, high);
        quickSort(array, low, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, high);
    }

    /**
     * Partitions the array around the pivot.
     * @param array array being sorted.
     * @param low lowest index.
     * @param high highest index.
     * @return final pivot location.
     */
    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    /**
     * Run validation tests.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort<Integer>());
        System.out.println("QuicksSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/QuickSort.java