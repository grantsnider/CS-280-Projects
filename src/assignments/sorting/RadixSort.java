package assignments.sorting;

/**
* RadixSort
*/
public class RadixSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public RadixSort() {}


    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new RadixSort<Integer>());

        System.out.println("RadixSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/RadixSort.java