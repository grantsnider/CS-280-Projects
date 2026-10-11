package assignments.datastructures;

import java.util.Iterator;
import java.util.Vector;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return length(root);
    }
    private int length(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + length(root.left) + length(root.right);
    }
    
    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return degree(root);
    }
    private int degree(Node root) {
        if (root == null) {
            return 0;
        }
        int current = 0;
        if (root.left != null) {
            current++;
        }
        if (root.right != null) {
            current++;
        }
        return Math.max(current, Math.max(degree(root.left), degree(root.right))
        );
    }
    
    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }
    private int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right)
        );
    }
    /**
     * The stored height of the node.
     * @param root the node being checked.
     * @return the height of the node.
     */
    private int nodeHeight(Node root) {
        if (root == null) {
            return 0;
        }

        return root.height;
    }
    
    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        Vector<T> data = new Vector<>();
        preorder(root, data);
        return data.iterator();
    }
    private void preorder(Node root, Vector<T> data) {
        if (root == null) {
            return;
        }
        data.add(root.data);
        preorder(root.left, data);
        preorder(root.right, data);
    }
    
    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        Vector<T> data = new Vector<>();
        inorder(root, data);
        return data.iterator();
    }
    private void inorder(Node root, Vector<T> data) {
        if (root == null) {
            return;
        }
        inorder(root.left, data);
        data.add(root.data);
        inorder(root.right, data);
    }
    
    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        Vector<T> data = new Vector<>();
        postorder(root, data);
        return data.iterator();
    }
    private void postorder(Node root, Vector<T> data) {
        if (root == null) {
            return;
        }
        postorder(root.left, data);
        postorder(root.right, data);
        data.add(root.data);
    }
    
    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        Vector<T> data = new Vector<>();
        if (root == null) {
            return data.iterator();
        }
        Queue<Node> pending = new CircularLinkedList<>();
        pending.enqueue(root);
        while (!pending.isEmpty()) {
            Node current = pending.dequeue();
            data.add(current.data);
            if (current.left != null) {
                pending.enqueue(current.left);
            }
            if (current.right != null) {
                pending.enqueue(current.right);
            }
        }
        return data.iterator();
    }
    
    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }
    
    /**
     * Check whether the value exists in the tree.
     * @param value the value being searched for.
     * @return true if it is found.
     */
    public boolean contains(T value) {
        return contains(root, value);
    }
    private boolean contains(Node root, T value) {
        if (root == null) {
            return false;
        }
        int compare = value.compareTo(root.data);
        if (compare == 0) {
            return true;
        }
        if (compare < 0) {
            return contains(root.left, value);
        }
        
        return contains(root.right, value);
    }

    /**
     * Add an value to the tree.
     * The value is inserted according to BST, but if the insertion is unbalanced, the rotation fixes it to a AVL.
     * @param value the value to insert.
     */
    public void add(T value) {
        root = add(root, value);
    }
    private Node add(Node root, T value) {
        if (root == null) {
            return new Node(value);
        }
        if (value.compareTo(root.data) < 0) {
            root.left = add(root.left, value);
        }
        else {
            root.right = add(root.right, value);
        }

        // AVL Balance.
        root.height = 1 + Math.max(nodeHeight(root.left), nodeHeight(root.right));
        int balance = balance(root);
        // Left-Left case.
        if (balance > 1 && value.compareTo(root.left.data) < 0) {
            return rotateRight(root);
        }
        // Right-Right case.
        if (balance < -1 && value.compareTo(root.right.data) > 0) {
            return rotateLeft(root);
        }
        // Left-Right case.
        if (balance > 1 && value.compareTo(root.left.data) > 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        // Right-Left case.
        if (balance < -1 && value.compareTo(root.right.data) < 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    /**
     * Removes an value from the tree.
     * The value removed maintains the BST. If needed, the rotation will fix the maintain the AVL balance.
     * @param value the value to remove.
     */
    public void remove(T value) {
        root = remove(root, value);
    }
    private Node remove(Node root, T value) {
        if (root == null) {
            return null;
        }
        int compare = value.compareTo(root.data);
        if (compare < 0) {
            root.left = remove(root.left, value);
        }
        else if (compare > 0) {
            root.right = remove(root.right, value);
        }
        else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node successful = root.right;
            while (successful.left != null) {
                successful = successful.left;
            }

            root.data = successful.data;
            root.right = remove(root.right, successful.data);
        }

        root.height = 1 + Math.max(nodeHeight(root.left), nodeHeight(root.right));
        int balance = balance(root);

        // Left-Left case.
        if (balance > 1 && balance(root.left) >= 0) {
            return rotateRight(root);
        }
        // Right-Right case.
        if (balance < -1 && balance(root.right) <= 0) {
            return rotateLeft(root);
        }
        // Left-Right case.
        if (balance > 1 && balance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        // Right-Left case.
        if (balance < -1 && balance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    /**
     * The balance of the node.
     * @param root the node whose balance is being checked.
     * @return the balanced node.
     */
    private int balance(Node root) {
        if (root == null) {
            return 0;
        }

        return nodeHeight(root.left) - nodeHeight(root.right);
    }

    /**
     * Performs a right rotation.
     * @param y the root being rotated.
     * @return the new root.
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node tempTree = x.right;
        x.right = y;
        y.left = tempTree;
        y.height = 1 + Math.max(nodeHeight(y.left), nodeHeight(y.right));
        x.height = 1 + Math.max(nodeHeight(x.left), nodeHeight(x.right));
        return x;
    }

    /**
     * Performs a left rotation.
     * @param x the root being rotated.
     * @return the new root.
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node tempTree = y.left;
        y.left = x;
        x.right = tempTree;
        x.height = 1 + Math.max(nodeHeight(x.left), nodeHeight(x.right));
        y.height = 1+ Math.max(nodeHeight(y.left), nodeHeight(y.right));
        return y;
    }

    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.
        int height;     // Tree height.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new AVLTree<>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        AVLTree<Integer> tree = new AVLTree<>();
        for (Integer number : numbers) {
            tree.add(number);
        }

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() <= 6;

        // Check traversals.
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});

        // Check rotations.
        // ll test.
        AVLTree<Integer> ll = new AVLTree<>();
        for (int n : new int[] {30, 20, 10}) ll.add(n);
        assert ll.height() == 2;
        // rr test.
        AVLTree<Integer> rr = new AVLTree<>();
        for (int n : new int[] {10, 20, 30}) rr.add(n);
        assert rr.height() == 2;
        // lr test.
        AVLTree<Integer> lr = new AVLTree<>();
        for (int n : new int[] {30, 10, 20}) lr.add(n);
        assert lr.height() == 2;
        // rl test.
        AVLTree<Integer> rl = new AVLTree<>();
        for (int n : new int[] {10, 30, 20}) rl.add(n);
        assert rl.height() == 2;

        System.out.println("AVLTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}

//java -cp lib/* -ea src/assignments/datastructures/AVLTree.java