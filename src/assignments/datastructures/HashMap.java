package assignments.datastructures;

import java.util.Iterator;

import adt.Map;
import adt.OrderedPair;

/**
* HashMap is a set of key-value pairs.
* The keys form a mathematical set: they are intrisncially unordered, and every key is unique. No constraint is placed on the values.
*/
public class HashMap<K,V> implements Map<K,V> {
    private Vector<Pair>[] hash; // Array buckets used by the hash table.
    private int M; // Number of buckets currently allocated.
    private int N; // Number of key-value pairs stored in the map.

    /**
    * Constructs an empty map, starts with 10 buckets, and no key-values. 
    */
    public HashMap() {
        M = 10; N = 0;
        hash = (Vector<Pair>[]) new Vector[M];
        for (int i = 0; i < M; i++) {
            hash[i] = new Vector<>();
        }
    }

    /**
    * Computes the bucket index for a key.
    * @param key the key being hashed.
    * @return the bucket index with the key.
    */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    /**
    * A key valeu-pair stored within any bucket.
    */
    private class Pair implements OrderedPair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K first() {
            return key;
        }
        public V second() {
            return value;
        }
        public OrderedPair<V, K> reversed() {
            V previousValue = value;
            K previousKey = key;
            return new OrderedPair<V, K>() {
                public V first() {
                    return previousValue;
                }
                public K second() {
                    return previousKey;
                }
                public OrderedPair<K, V> reversed() {
                    return Pair.this;
                }
            };
        }
    }

    /**
     * Rebuilds the hash table using twice the amount of buckets.
     */
    private void rehash() {
        Vector<Pair>[] previous = hash;
        M *= 2;
        hash = (Vector<Pair>[]) new Vector[M];
        for (int i = 0; i < M; i++) {
            hash[i] = new Vector<>();
        }
        N = 0;
        for (Vector<Pair> bucket : previous) {
            for (Pair pair : bucket) {
                put(pair.first(), pair.second());
            }
        }
    }

    /**
    * Checks if the map contains a given key.
    * @param key the key to look for.
    * @return true iff the map associates key to some value.
    */
    public boolean contains(K key) {
        int bucket = hash(key);
        for (Pair pair : hash[bucket]) {
            if (pair.first().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
    * Fetches the value assocaited with a given key.
    * @param key the key to look for.
    * @return the value associated with the key, or null if not found.
    */
    public V get(K key) {
        int bucket = hash(key);
        for (Pair pair : hash[bucket]) {
            if (pair.first().equals(key)) {
                return pair.second();
            }
        }
        return null;
    }

    /**
    * Computes the number of items in this collection.
    * @return the number of items.
    */
    public int length() {
        return N;
    }

    /**
    * Insert of replace a key-value pair in the map.
    * @param key the key to look for.
    * @param value the value to assign to key.
    * @return the value previously associated with key, or null if not in the map.
    */
    public V put(K key, V value) {
        int bucket = hash(key);
        for (int i = 0; i < hash[bucket].length(); i++) {
            Pair pair = hash[bucket].at(i);
            if (pair.first().equals(key)) {
                V old = pair.second();
                hash[bucket].set(i, new Pair(key, value));
                return old;
            }
        }
        if (hash[bucket].length() >= 5) {
            rehash();
            bucket = hash(key);
        }
        hash[bucket].push(new Pair(key, value));
        N++;
        return null;
    }

    /**
    * Remove a key-value pair from the map.
    * @param key the key to look for.
    * @return the value previouly associated with key, or null if not in the map.
    */
    public V remove(K key) {
        int bucket = hash(key);
        for (int i = 0; i < hash[bucket].length(); i++) {
            Pair pair = hash[bucket].at(i);
            if (pair.first().equals(key)) {
                V old = pair.second();
                hash[bucket].delete(i);
                N--;
                return old;
            }
        }
        return null;
    }

    /** 
    * Creates an iterator over all the key-value pairs in the map.
    * @return the iterator over the maps content.
    */
    public Iterator<OrderedPair<K, V>> iterator() {
        Vector<OrderedPair<K, V>> traversal = new Vector<>();
        for (int bucket = 0; bucket < M; bucket++) {
            for (int i = 0; i < hash[bucket].length(); i++) {
                traversal.push(hash[bucket].at(i));
            }
        }
        return traversal.iterator();
    }


    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        Map.validate(new HashMap<>());
        HashMap<Integer, Integer> test = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            test.put(i, i);
        }
        for (int i = 0; i < 100; i++) {
            assert test.contains(i);
            assert test.get(i).equals(i);
        }
        System.out.println("HashMap passes all tests.");   
     
    }
}

//java -cp lib/* -ea src/assignments/datastructures/HashMap.java