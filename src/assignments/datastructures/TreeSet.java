package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
* TreeSet is the data structure representing a mathematical set. The set is intrisically unordered, and the items are unique.
*/
public class TreeSet<T extends Comparable<T>> implements Set<T> {    
    private BinarySearchTree<T> tree;

    public TreeSet() {
        this.tree = new BinarySearchTree<>();
    }

    /**
    * Creates an iterator that goes through the tree.
    * @return the tree iterated.
    */
    public Iterator<T> iterator() {
        return this.tree.iterator();
    }
    
    /**
    * Adds the items to the set.
    * @param value the value being added.
    * @return true if found, false otherwise.
    */
    public boolean add(T value) {
        if (tree.contains(value)) {
            return false;
        }
        tree.add(value);
        return true;
    }

    /**
    * Computes the number of items in the collection.
    * @param value checking if the value exists.
    * @return the number of items.
    */
    public boolean contains(T value) {
        return this.tree.contains(value);
    }

    /**
    * Computes the number of items in the tree.
    * @return the number of items.
    */
    public int length() {
        return this.tree.length();
    }

    /**
    * Removes an item from the tree.
    * @param value the value to remove
    * @return true if the set had changed.
    */
    public boolean remove(T value) {
        if (!tree.contains(value)) {
            return false;
        }
        tree.remove(value);
        return true;
    }

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        Set.validate(new TreeSet<>());

        System.out.println("TreeSet passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/TreeSet.java