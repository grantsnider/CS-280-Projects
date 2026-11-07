package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
* HashSet is an function that takes an obkect, and spits out an integer. It is intrinsically unordered, and all the items are unique.
*/
public class HashSet<T extends Comparable<T>> implements Set<T> {
    private Vector<T> [] hash;
    private int N; // The number of buckets, in our array/
    private int M; // The number of items in our hash set.
    private int D; // The maximum number of items in any one bucket.

    /**
    * Creates an iterator to go throughout the hash.
    * @return the hash iterated.
    */
    public Iterator<T> iterator() {
    
    }

    /**
    * Adds an item in the form of an object to the hash.
    * @param object the object to add to the hash.
    * @return true if the hash is added.
    */
    public boolean add(T object) {
    
    }

    /**
    * Checks if the object is within the hash.
    * @param object the object to look for.
    * @return the object.
    */
    public boolean contains(T object) {
    
    }

    /**
    * Computes the number of objects in the hash.
    * @return the number of objects.
    */
    public int length() {

    }

    /**
    * Removes an object from the hash.
    * @param object the object to remove.
    * @return true if the hash had changed.
    */
    public boolean remove(T value) {
    
    }

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        Set.validate(new HashSet<>());

        System.out.println("HashSet passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/HashSet.java