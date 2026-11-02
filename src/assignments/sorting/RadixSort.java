package assignments.sorting;

/**
* RadixSort sorts an integer by processing one digit at a time, right to left.
*/
public class RadixSort extends SortingAlgorithm<Integer> {
    public RadixSort() {}

    /**
     * Sort an array in-place using radix sort.
     * @param array values to sort.
     */
    public void sort(Integer[] array) {
        if (array.length == 0) {
            return;
        }
        int min = array[0];
        int max = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        if (min < 0) {
            for ( int i = 0; i < array.length; i++) {
                array[i] = array[i] - min;
            }
            max = max - min;
        }
        for (int place = 1; max / place > 0; place *= 10) {
            Integer[][] buckets = new Integer[10][array.length];
            int[] bucketSize = new int[10];
            for (int value : array) {
                int digit = (value / place) % 10;
                buckets[digit][bucketSize[digit]] = value;
                bucketSize[digit]++;
            }
            int index = 0;
            for (int bucket = 0; bucket < 10; bucket++) {
                for(int i = 0; i < bucketSize[bucket]; i++) {
                    array[index] = buckets[bucket][i];
                    index++;
                } 
            }
        }
        if (min < 0) {
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] + min;
            }
        }
    }
    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new RadixSort());

        System.out.println("RadixSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/RadixSort.java