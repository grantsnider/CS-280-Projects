package assignments.sorting;

import java.util.Arrays;

/**
 * MergeSort splits data into subgroups and recursively merges them.
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sorts the array.
     * @param array array to sort.
     */
    public void sort(T[] array) {
        T[] work = Arrays.copyOf(array, array.length);
        mySort(array, work, 0, array.length - 1);
    }

    /**
     * Recursively sorts an array.
     * @param array the array thats sorted.
     * @param work array for merging.
     * @param left left boundary.
     * @param right right boundary.
     */
    private void mySort(T[] array, T[] work, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mySort(array, work, left, mid);
        mySort(array, work, mid + 1, right);
        merge(array, work, left, mid, right);
    }

    /**
     * Merges the two sorted arrays.
     * @param left first array sorted.
     * @param right second array sorted.
     * @return merged sorted array.
     */
    private void merge(T[] array, T[] work, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            work[i] = array[i];
        }
        int l = left;
        int r = mid + 1;
        int m = left;
        while (l <= mid && r <= right) {
            if (work[l].compareTo(work[r]) <= 0) {
                array[m++] = work[l++];
            }
            else {
                array[m++] = work[r++];
            }
        }
        while (l <= mid) {
            array[m++] = work[l++];
        }
        while (r <= right) {
            array[m++] = work[r++];
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args.
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort<Integer>());

        System.out.println("MergeSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/MergeSort.java