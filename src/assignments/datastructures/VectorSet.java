package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
* VectorSet is the data structure representing a mathematical set. The set is intrisically unordered, and the items are unique.
*/
public class VectorSet<T extends Comparable<T>> implements Set<T> {
    private Vector<T> vector;

    public VectorSet() {
        this.vector = new Vector<>();
    }

    /**
    * Creates an iterator that goes through the vector.
    * @return the vector iterated.
    */
    public Iterator<T> iterator() {
        return this.vector.iterator();
    }

    /**
    * Adds an items to the set. 
    * @return true if found, false otherwise.
    */
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        this.vector.push(value);
        return true;
    }

    /**
    * Checks if the collection contains a given value.
    * @param value the value to look for.
    * @return true if the collection contains a value.
    */
    public boolean contains(T value) {
        return this.vector.contains(value);
    }

    /**
    * Computes the number of items in the collection.
    * @return the number of items.
    */
    public int length() {
        return this.vector.length();
    }

    /**
    * Removes an items from the set.
    * @param value the value to remove.
    * @return true if the set has changed.
    */
    public boolean remove(T value) {
        for (int i = 0; i < this.vector.length(); i++) {
            if (value.equals(this.vector.at(i))) {
                this.vector.delete(i);
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
        Set.validate(new VectorSet<>());

        System.out.println("VectorSet passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/VectorSet.java