package assignments.datastructures;

import java.util.Iterator;

import adt.PriorityQueue;

/**
 * An PriorityEnqueuer maintains a list in enqueue order.
 * When enqueueing, it searches the whole list for the smallest number.
 * @param <T>
 */
public class PriorityEnqueuer<T extends Comparable<T>> implements PriorityQueue<T> {
    /**
     * Determine whether the queue is empty.
     * @return true if the queue is empty.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * Adds an item to the back of the queue.
     * This operation executes within O(1) time.
     * @param value to the enqueue.
     */
    public void enqueue(T value) {
        Node newest = new Node(value, null);
        if (tail == null) {
            tail = newest;
            tail.link = tail;
        }
        else {
            newest.link = tail.link;
            tail.link = newest;
            tail = newest;
        }
        size++;
    }
    /**
     * Remove and return the item in the front.
     * @return removed value.
     */
    public T dequeue() {
        assert !isEmpty();
        Node minimumBefore = tail;
        Node minimumNode = tail.link;

        Node before = tail.link;
        Node current = tail.link.link;
        while (current != tail.link) {
            if (current.data.compareTo(minimumNode.data) < 0) {
                minimumNode = current;
                minimumBefore = before;
            }
            before = current;
            current = current.link;
        }
        T result = minimumNode.data;
        if (size == 1) {
            tail = null;
        }
        else {
            minimumBefore.link = minimumNode.link;
            if (minimumNode == tail) {
                tail = minimumBefore;
            }
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
        Node current = tail.link;
        T minimum = current.data;
        current = current.link;
        while (current != tail.link) {
            if (current.data.compareTo(minimum) < 0) {
                minimum = current.data;
            }
            current = current.link;
        }
        return minimum;
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

        // Method tests.
        PriorityEnqueuer<Integer> p = new PriorityEnqueuer<>();
        assert p.isEmpty();
        p.enqueue(5); p.enqueue(10); p.enqueue(3);
        assert !p.isEmpty();
        assert p.peek() == 3;
        assert p.dequeue() == 3;
        assert p.peek() == 5;
        assert p.dequeue() == 5; assert p.dequeue() == 10;

        System.out.println("PriorityEnqueuer passes all tests.");

    }
}

//java -cp lib/* -ea src/assignments/datastructures/PriorityEnqueuer.java