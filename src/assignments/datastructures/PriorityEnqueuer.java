package assignments.datastructures;

import java.util.Iterator;

import adt.PriorityQueue;

/**
 * An PriorityEnqueuer maintains a list in enqueue order.
 * When dequeing, it searches the whole lsit for the smallest number.
 * @param <T>
 */
public class PriorityEnqueuer<T> implements PriorityQueue<T> {
    /**
     * Determine whether the queue is empty.
     * @return true if the queue is empty.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * Adds an item to the back of the queue.
     * @param value to the enqueue.
     */
    public void enqueue(T value) {
        insert(size, value);
    }
    /**
     * Remove and return the item in the front.
     * @return removed value.
     */
    public T dequeue() {
        assert !isEmpty();
        return delete(0);
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
     * Intilize an empty priorityenqueuer.
     */
    public PriorityEnqueuer() {
        this.tail = null;
        this.size = 0;
    }

    /**
    * A node in the priorityenqueuer.
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
        PriorityQueue.validate(new PriorityEnqueuer<>());

        PriorityEnqueuer<Integer> p = new PriorityEnqueuer<>();
        System.out.println("PriorityEnqueuer passes all tests.");

    }
}