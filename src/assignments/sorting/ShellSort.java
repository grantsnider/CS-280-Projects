package assignments.sorting;

/**
* ShellSort
*/
public class ShellSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public ShellSort() {}

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new ShellSort<Integer>());

        System.out.println("ShellSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/ShellSort.java