package assignments.datastructures;

import java.util.Iterator;

import adt.PriorityQueue;

/**
 * An PriorityDequeuer maintains a list in sorted order.
 * When dequeing, just pop off the last element.
 * @param <T>
 */
public class PriorityDequeuer<T extends Comparable<T>> implements PriorityQueue<T> {
    /**
     * Determine whether the priority queue is empty.
     * @return true if the queue is empty.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * Inserts a value into the priority queue.
     * @param value to the enqueue.
     * @return returns the value.
     */
    public void enqueue(T value) {
        Node newest = new Node(value, null);
        if (tail == null) {
            tail = newest;
            tail.link = tail;
            size++;
            return;
        }
        Node before = tail;
        Node current = tail.link;
        while (current != tail) {
            if (value.compareTo(current.data) < 0) {
                before.link = newest;
                newest.link = current;

                if (current == tail.link) {
                    tail.link = newest;
                }
                size++;
                return;
            }
            before = current;
            current = current.link;
        }
        if (value.compareTo(current.data) < 0) {
            before.link = newest;
            newest.link = current;
            size++;
            return;
        }
        newest.link = tail.link;
        tail.link = newest;
        tail = newest;
        size++;
    }
    /**
     * Remove and return the high-priority value.
     * @return removed value.
     */
    public T dequeue() {
        assert !isEmpty();
        Node front = tail.link;
        T result = front.data;
        if (size == 1) {
            tail = null;
        }
        else {
            tail.link = front.link;
        }
        size--;
        return result;
    }
    /**
     * Views the item in the front, doesn't remove it.
     * @return the front value.
     */
    public T peek() {
        assert !isEmpty();
        return tail.link.data;
    }
    
    private Node tail;
    private int size;

    /**
     * Intilize an empty prioritydequeuer.
     */
    public PriorityDequeuer() {
        this.tail = null;
        this.size = 0;
    }

    /**
    * A node in the prioritydequeuer to store a value added.
    */
    private class Node {
        T data;
        Node link;
        /**
         * Creates the node.
         * @param data the stored value.
         * @param link the reference to the next node.
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args.
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new PriorityDequeuer<>());

        // Method tests.
        PriorityDequeuer<Integer> p = new PriorityDequeuer<>();
        assert p.isEmpty();
        p.enqueue(5); p.enqueue(10); p.enqueue(3);
        assert !p.isEmpty();
        assert p.peek() == 3;
        assert p.dequeue() == 3;
        assert p.peek() == 5; 
        assert p.dequeue() == 5; assert p.dequeue() == 10;

        System.out.println("PriorityDequeuer passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/PriorityDequeuer.java