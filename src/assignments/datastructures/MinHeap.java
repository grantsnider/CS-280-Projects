package assignments.datastructures;

import java.util.Vector;
import java.util.Iterator;
import java.util.LinkedList;

import adt.PriorityQueue;
import adt.Tree;
/**
 * MinHeap stores values in Vector and keeps the smallest value by the root.
 * Added new values to maintain balance, and the parent must be less than or equal to its children.
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T>, Tree<T> {
    /**
     * Initilize an empty MinHeap.
     */
    public MinHeap() {
        heap = new Vector<>();
    }
    // The PriorityQueue Methods.
    /**
     * Determines whether the heap has values.
     *@return true if the heap is empty.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    /**
     * Views the minimum value without removing it from the heap.
     * The runtime is always O(1).
     * @return the smallest value.
     */
    public T peek() {
        assert !isEmpty();
        return heap.get(0);
    }
    /**
     * Inserts an value into the heap.
     * The operation executes at O(log n).
     * @param value the value being added.
     */
    public void enqueue(T value) {
        heap.add(value);
        int child = heap.size() - 1;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (heap.get(child).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            T temp = heap.get(child);
            heap.set(child, heap.get(parent));
            heap.set(parent, temp);
            child = parent;
        }
    }
    /**
     * Remove and returns the minimum value from heap.
     * This method runs at O(log n).
     * @return returning the deleted value.
     */
    public T dequeue() {
        assert !isEmpty();
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (heap.isEmpty()) {
            return min;
        }
        heap.set(0, last);
        int parent = 0;
        while(true) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int small = parent;
            if (left < heap.size() && heap.get(left).compareTo(heap.get(small)) < 0) {
                small = left;
            }
            if (right < heap.size() && heap.get(right).compareTo(heap.get(small)) < 0) {
                small = right;
            }
            if (small == parent) {
                break;
            }
            T temp = heap.get(parent);
            heap.set(parent, heap.get(small));
            heap.set(small, temp);
            parent = small;
        }

        return min;
    }
    // The Tree Methods.
    /**
     * Computes the values in the heap.
     * @return the number of values.
     */
    public int length() {
        return heap.size();
    }
    /**
     * Computes maximum degree for the heap.
     * @return the maximum degree.
     */
    public int degree() {
        if (heap.isEmpty()) {
            return 0;
        }
        if (heap.size() == 1) {
            return 0;
        }
        if (heap.size() == 2) {
            return 1;
        }
        return 2;
    }
    /**
     * Computes the height of the heap.
     * @return the heap height.
     */
    public int height() {
        if (heap.isEmpty()) {
            return 0;
        }
        int count = heap.size();
        int height = 0;
        while (count > 0) {
            count /= 2;
            height++;
        }
        return height;
    }
    /**
     * Performs levelorder traversal, the top to bottom.
     * @return a iterator.
     */
    public Iterator<T> levelorder() {
        Vector<T> traversal = new Vector<>();
        for (T value : heap) {
            traversal.add(value);
        }
        return traversal.iterator();
    }
    /**
     * Iterates using inorder traversal through the heap.
     * @return returns an iterator.
     */
    public Iterator<T> iterator() {
        return inorder();
    }
    /**
     * Performs preorder traversal through the heap.
     * Visited root-left-right.
     * @return returns the traversal.
     */
    public Iterator<T> preorder() {
        Vector<T> traversal = new Vector<>();
        LinkedList<Integer> pending = new LinkedList<>();
        if (heap.isEmpty()) {
            return traversal.iterator();
        }
        pending.add(0);
        while (!pending.isEmpty()) {
            int current = pending.pop();
            traversal.add(heap.get(current));
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            if (right < heap.size()) {
                pending.push(right);
            }
            if (left < heap.size()) {
                pending.push(left);
            }

        }
        return traversal.iterator();
    }
    /**
     * Performs postorder traversal through the heap.
     * Visited left-right-root.
     * @return returns the traversal.
     */
    public Iterator<T> postorder() {
        Vector<T> traversal = new Vector<>();
        Vector<T> reversed = new Vector<>();
        if (heap.isEmpty()) {
            return traversal.iterator();
        }       
        LinkedList<Integer> pending = new LinkedList<>();
        pending.push(0);
        while (!pending.isEmpty()) {
            int current = pending.pop();
            traversal.add(heap.get(current));
            int right = 2 * current + 2;
            int left = 2 * current + 1;
            if (left < heap.size()) {
                pending.push(left);
            }
            if (right < heap.size()) {
                pending.push(right);
            }
        }
        for (int i = traversal.size() - 1; i >= 0; i--) {
            reversed.add(traversal.get(i));
        }
        return reversed.iterator();
    }
    /**
     * Performs an inorder traversal through the heap.
     * Visited left-root-right order.
     * @return returns the traversal.
     */
    public Iterator<T> inorder() {
        Vector<T> traversal = new Vector<>();
        LinkedList<Integer> pending = new LinkedList<>();
        int current = 0;
        while (!pending.isEmpty() || current < heap.size()) {
            while (current < heap.size()) {
                pending.push(current);
                current = 2 * current + 1;
            }
            current = pending.pop();
            traversal.add(heap.get(current));
            current = 2 * current + 2;
        }
        return traversal.iterator();
    }
    /**
     * Storage for the heap.
     */
    private Vector<T> heap;
    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        PriorityQueue.validate(new MinHeap<>());
        Tree.validate(new MinHeap<>());

        // Basic Method tests.
        MinHeap<Integer> heap = new MinHeap<>();
        assert heap.isEmpty();
        assert heap.length() == 0;
        heap.enqueue(4); heap.enqueue(2); heap.enqueue(6); heap.enqueue(1); heap.enqueue(3);
        assert heap.peek() == 1;
        assert heap.length() == 5;
        assert !heap.isEmpty();
        assert heap.dequeue() == 1;
        assert heap.dequeue() == 2;
        assert heap.peek() == 3;
        assert heap.length() == 3;
        heap.enqueue(-1);
        assert heap.peek() == -1;
        assert heap.dequeue() == -1; assert heap.dequeue() == 3; assert heap.dequeue() == 4; assert heap.dequeue() == 6;
        assert heap.isEmpty();

        //Traversal Method tests.
        MinHeap<Integer> tree = new MinHeap<>();
        tree.enqueue(4); tree.enqueue(2); tree.enqueue(6); tree.enqueue(1); tree.enqueue(3); tree.enqueue(5); tree.enqueue(7);
        Iterator<Integer> order = tree.preorder();
        assert order.next() == 1; assert order.next() == 2; assert order.next() == 4;
        order = tree.inorder();
        assert order.next() == 4; assert order.next() == 2; assert order.next() == 3;
        order = tree.postorder();
        assert order.next() == 4; assert order.next() == 3; assert order.next() == 2;
        order = tree.levelorder();
        assert order.next() == 1; assert order.next() == 2; assert order.next() == 5;

        System.out.println("MinHeap passes all tests.");
    }
}

//java -cp lib/* -ea src/assignments/datastructures/MinHeap.java