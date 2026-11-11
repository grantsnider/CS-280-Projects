package assignments.sorting;

/**
* BucketSort prepares an array of buckets, then puts the items into their bucket, then takes the iteam out of the bucket.
*/
public class BucketSort extends SortingAlgorithm<Integer> {
    public BucketSort() {}

    /**
     * Sorts an array in-place using bucket sort.
     * Counts the number of occurences of each value, then puts them back into the array sorted.
     * @param array the values to sort.
     */
    public void sort(Integer[] array) {
        // Creates an empty array.
        if (array.length == 0) {
            return;
        }

        // Finds the minimum and maximum values.
        int min = array[0];
        int max = array[0];
        
        for (int value : array) {
            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }

        }

        int[] buckets = new int[max - min + 1];

        // Counts the occurences of each value.
        for (int value : array) {
            buckets[value - min]++;
        }

        // Rebuilds the array to a sorted order.
        int index = 0;
        for (int value = 0; value < buckets.length; value++) {
            while (buckets[value] > 0) {
                array[index] = value + min;

                index++;

                buckets[value]--;
            }
        }
    }

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BucketSort());

        System.out.println("BucketSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/BucketSort.java