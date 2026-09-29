package assignments.sorting;

/**
 * MergeSort splits data into subgroups and recursively merges them.
 */
public class MergeSort extends SortingAlgorithm<Integer> {
    
    /**
     * Sorts the array.
     * @param array array to sort.
     */
    public void sort(Integer[] array) {
        Integer [] sorted = mySort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = sorted[i];
        }
    }

    /**
     * Recursively sorts an array.
     * @param array the array thats sorted.
     * @return sorted copy of array.
     */
    private Integer[] mySort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        Integer [] left = new Integer[mid];
        Integer [] right = new Integer[array.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        left = mySort(left);
        right = mySort(right);
        return merge(left, right);
    }

    /**
     * Merges the two sorted arrays.
     * @param left first array sorted.
     * @param right second array sorted.
     * @return merged sorted array.
     */
    private Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] merged = new Integer[left.length + right.length];
        int l = 0;
        int r = 0;
        int m = 0;
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                merged[m++] = left[l++];
            }
            else {
                merged[m++] = right[r++];
            }
        }
        while (l < left.length) {
            merged[m++] = left[l++];
        }
        while (r < right.length) {
            merged[m++] = right[r++];
        }

        return merged;
    }

    /**
     * Run validation tests.
     * @param args command-line args.
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort());

        System.out.println("MergeSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/MergeSort.java