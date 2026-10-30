package assignments.sorting;

/**
* BucketSort prepares an array of buckets, then puts the items into their bucket, then takes the iteam out of the bucket.
*/
public class BucketSort extends SortingAlgorithm<Integer> {
    public BucketSort() {}

    /**
     * Sorts an array in-place using bucket sort.
     */

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BucketSort<Integer>());

        System.out.println("BucketSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/BucketSort.java