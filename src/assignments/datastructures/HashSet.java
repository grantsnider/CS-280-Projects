package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
* HashSet is an function that takes an obkect, and spits out an integer. It is intrinsically unordered, and all the items are unique.
*/
public class HashSet<T> implements Set<T> {
    private Vector<T>[] hash; // Storage for the hash table buckets.
    private int M; // The number of buckets in the hash table.
    private int N; // The number of values stored in the set.
    private static final int D = 5; // Maximum bucket size.

    /**
    * Initialize an empty HashSet.
    */
    public HashSet() {
        M = 10; N = 0;
        hash = (Vector<T>[]) new Vector[M];
        for (int i = 0; i < hash.length; i++) {
            hash[i] = new Vector<>();
        }
    }

    /**
    * Computes a bucket index for a given value.
    * @param value the value being hashed.
    * @param buckets the number of buckets.
    * @return a bucket index.
     */
    private int hash(T value, int buckets) {
        return Math.abs(value.hashCode()) % buckets;
    }

    /**
     * Rebuilds the hash table using twice as many buckets, then inserts the values.
     * 
     */
    private void rehash() {
        Vector<T>[] previous = hash;
        hash = (Vector<T>[]) new Vector[previous.length * 2];
        for (int i = 0; i < hash.length; i++) {
            hash[i] = new Vector<>();
        }
        for (Vector<T> bucket : previous) {
            for (T value : bucket) {
                int index = hash(value, hash.length);
                hash[index].push(value);
            }
        }
    }

    /**
    * Creates an iterator to visit the values.
    * @return the iterator over the values.
    */
    public Iterator<T> iterator() {
        Vector<T> traversal = new Vector<>();
        for (int i = 0; i < hash.length; i++) {
            for (int j = 0; j < hash[i].length(); j++) {
                traversal.insert(traversal.length(), hash[i].at(j));
            }
        }
        return traversal.iterator();
    }

    /**
    * Add an item to the set.
    * @param value the value to add.
    * @return true iff the set changed.
    */
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        int bucket = hash(value, hash.length);

        if (hash[bucket].length() >= D) {
            rehash();
            bucket = hash(value, hash.length);
        }
        hash[bucket].push(value);
        N++;
        return true;
    }

    /**
    * Check if the collection contains a given value.
    * @param value the value to look for.
    * @return true iff the collection contains value.
    */
    public boolean contains(T value) {
        int bucket = hash(value, hash.length);
        return hash[bucket].contains(value);
    }

    /**
    * Compute the number of items in this collection.
    * @return the number of items.
    */
    public int length() {
        return N;
    }

    /**
    * Remove an item from the set.
    * @param value the value to remove.
    * @return true iff the set changed.
    */
    public boolean remove(T value) {
        int bucket = hash(value, hash.length);
        for (int i = 0; i < hash[bucket].length(); i++) {
            if (hash[bucket].at(i).equals(value)) {
                hash[bucket].delete(i);
                N--;
                return true;
            }
        }
        return false;
    }

    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        Set.validate(new HashSet<>());

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assert set.contains(i);
        }
        assert set.length() == 100;
        System.out.println("HashSet passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/HashSet.java