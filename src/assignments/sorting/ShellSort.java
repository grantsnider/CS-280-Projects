package assignments.sorting;

/**
* ShellSort performs insertion sorts using the gap size until it becomes one.
*/
public class ShellSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public ShellSort() {}

    /**
    * Sorts an array in-place using shell sort.
    * @param array values to sort.
    */
    public void sort(T[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                T current = array[i];
                int j = i;
                for (j = i; j >= gap && current.compareTo(array[j - gap]) < 0; j -= gap) {
                    array[j] = array[j -gap];
                }
                array[j] = current;
            }
        }
    }
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