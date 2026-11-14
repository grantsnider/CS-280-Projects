package assignments.algorithms;

import assignments.datastructures.HashSet;
import assignments.datastructures.TreeSet;
import assignments.datastructures.VectorSet;

/**
* ERASearching measures the runtime of unsuccessful search operations, in three set implementations.
*/
public class ERASearching {
    
    /**
     * Measures the runtime of a HashSet.
     * @param n the size of the set.
     * @return runtime in nanoseconds
     */
    public static long timeHashSet(int n) {
        HashSet<Integer> hash = new HashSet<>();
        Integer[] values = randomValues(n);
        for (int value : values) {
            hash.add(value);
        }
        long begin = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            hash.contains(n + 1);
        }
        return System.nanoTime() - begin;
    }

    /**
     * Measures the runtime of a TreeSet.
     * @param n the size of the set.
     * @return the runtime in nanoseconds.
     */
    public static long timeTreeSet(int n) {
        TreeSet<Integer> tree = new TreeSet<>();
        Integer[] values = randomValues(n);
        for (int value : values) {
            tree.add(value);
        }
        long begin = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            tree.contains(n + 1);
        }
        return System.nanoTime() - begin;
    }

    /**
    * Measures the runtime of a VectorSet.
    * @param n the size of the set.
    * @return the runtime in nanoseconds.
    */
    public static long timeVectorSet(int n) {
        VectorSet<Integer> vector = new VectorSet<>();
        Integer[] values = randomValues(n);
        for (int value : values) {
            vector.add(value);
        }
        long begin = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            vector.contains(n + 1);
        }
        return System.nanoTime() - begin;
    }

    /**
     * Generates the integers 1 through N in a random order.
     * @param n the number of values generated.
     * @return the array with the integers.
     */
    private static Integer[] randomValues(int n) {
        Integer[] values = new Integer[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        for (int i = n - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
        return values;
    }

    /**
    * Generates the runtime for several datastructure implementations.
    * @param args command-line arguments.
    */
    public static void main(String[] args) {
        System.out.println("N, HashSet, TreeSet, VectorSet");
        for (int n = 1000; n <= 4096000; n *= 2) {
            System.out.println(n + ", " + timeHashSet(n) + ", " + timeTreeSet(n) + "," + timeVectorSet(n));
        }
    }

}

//java -cp lib/* -ea src/assignments/algorithms/ERASearching.java