package assignments.sorting;

/**
 * Quicksort sorts values relative to a pivot.
 */
public class QuickSort extends SortingAlgorithm<Integer> {
    
    /**
     * Intialize the QuickSort.
     */
    public QuickSort() {}

    /**
     * Sorts the array.
     * @param array the array to sort.
     */
    public void sort(Integer[] array) {
        Integer[] sorted = mySort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = sorted[i];
        }
    }

    /**
     * Recursively sorts the array.
     * @param array the array to sort.
     * @return a sorted copy of the array.
     */
    private Integer[] mySort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        Integer pivot = array[0];
        int leftC = 0;
        int rightC = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= pivot) {
                leftC++;
            }
            else {
                rightC++;
            }
        }
        Integer[] left = new Integer[leftC];
        Integer[] right = new Integer[rightC];
        int l = 0;
        int r = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= pivot) {
                left[l++] = array[i];
            }
            else {
                right[r++] = array[i];
            }
        }
        left = mySort(left);
        right = mySort(right);
        Integer[] sorted = new Integer[array.length];
        int index = 0;
        for (Integer x : left) {
            sorted[index++] = x;
        }
        sorted[index++] = pivot;
        for (Integer x : right) {
            sorted[index++] = x;
        }
        
        return sorted;
    }

    /**
     * Run validation tests.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort());

        System.out.println("QuicksSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/QuickSort.java