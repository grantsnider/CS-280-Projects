package assignments.datastructures;

import java.util.Iterator;

import org.w3c.dom.Node;

import adt.List;

/**
 * A circular linked implementation of the list interface.
 * The last node links to the first, forming a circle. The list maintains a reference of the tail and the size.
 * 
 * @param <T> the element type stored in the list.
 */
public class CircularLinkedList<T> implements List<T>, Iterable<T> {
    private Node tail;
    private int size;

    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    
    /**
    * Computer number of items in list.
    * 
    * @return the number of items.
    */
    public int length() {
        return this.size;
    }
    
    /**
    * Fetch an item from list
    * 
    * @param index the location of the item
    * @return the stored value
    */
    public T at(int index) {
        assert 0 <= index && index < size;
        Node cursor = tail.link;
        for (int i = 0; i < index; i++) {
            cursor = cursor.link;
        }

        return cursor.data;
    }

    /**
    * Change the item in the list.
    * 
    * @param index the location of the item
    * @param value the new value
    */
    public void set(int index, T value) {
        assert 0 <= index && index < size;
        Node cursor = tail.link;
        for(int i = 0; i < index; i++) {
            cursor = cursor.link;
        }
        
        cursor.data = value;
    }

    /**
    * Check if value is in the list.
    * 
    *@param value the value being searched
    * @return true if value is in the list
    */
    public boolean contains(T value) {
        if (size == 0)
            return false;
        Node cursor = tail.link;
        for (int i = 0; i < size; i++) {
            if (cursor.data.equals(value))
                return true;
            cursor = cursor.link;
        }

        return false;
    }

    /**
     * Insert value into the list
     * 
     * @param index insertion location
     * @param value to insert
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= size;
        if(size == 0) {
            tail = new Node(value, null);
            tail.link = tail;
        }
        else if(index == 0){
            tail.link = new Node(value, tail.link);
        }
        else if(index == size){
            Node newNode = new Node(value, tail.link);
            tail.link = newNode;
            tail = newNode;
        }
        else{
            Node cursor = tail.link;
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.link;
            }

            cursor.link = new Node(value, cursor.link);
        }

        size++;
    }

    /**
    * Delete an item from list
    * 
    * @param index location to remove
    * @return the removed value
    */
    public T delete(int index) {
        assert 0 <= index && index < size;
        T removed;
        if (size == 1) {
            removed = tail.data;
            tail = null;
        }
        else if (index == 0) {
            removed = tail.link.data;
            tail.link = tail.link.link;
        }
        else {
            Node cursor = tail.link;
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.link;
            }
            
            removed = cursor.link.data;
            if (cursor.link == tail) {
                tail = cursor;
            }

            cursor.link = cursor.link.link;
        }

        size --;
        return removed;
    }

    /**
     * Create an iterator over list
     * 
     * @return iterator
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node cursor = (tail == null?null: tail.link);
            private int count = 0;
            public boolean hasNext() {
                return count < size;
            }
            public T next() {
                T value = cursor.data;
                cursor = cursor.link;
                count++;
                return value;
            }
        };
    }

    /**
    * Node Structure
    */
    private class Node {
        T data;
        Node link;
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
    * Run validation test
    *
    * @param args command-line arguments
    */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i = 0; i < 5; i++)
            list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --)
            assert iter.next().equals(i - 1);
        assert !iter.hasNext();
        System.out.println("CircularLinkedList passes all tests.");
    }

}