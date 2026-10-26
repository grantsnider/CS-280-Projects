package assignments.sorting;

import assignments.datastructures.BinarySearchTree;

import java.util.Iterator;

/**
 * TreeSort puts all items into a BinarySearchTree, then traverses the tree.
 */
public class TreeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sorts using the BinarySearchTree.
     * @param array the array being sorted.
     */
    public void sort(T[] array) {
        BinarySearchTree<T> tree = new BinarySearchTree<>();

        for (T value : array) {
            tree.add(value);
        }

        Iterator<T> sorted = tree.inorder();

        int i = 0;

        while (sorted.hasNext()) {
            array[i] = sorted.next();
            i++;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args.
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new TreeSort<Integer>());

        System.out.println("TreeSort passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/sorting/TreeSort.java