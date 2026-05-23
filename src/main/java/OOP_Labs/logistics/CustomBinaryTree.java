package OOP_Labs.logistics;

import java.util.ArrayList;
import java.util.List;

public class CustomBinaryTree<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // ДОДАВАННЯ елмента
    public void add(T data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node current, T data) {
        if (current == null) {
            return new Node(data);
        }
        if (data.compareTo(current.data) < 0) {
            current.left = insertRecursive(current.left, data);
        } else {
            current.right = insertRecursive(current.right, data);
        }
        return current;
    }

    // ВИДАЛЕННЯ елемента
    public void remove(T data) {
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node current, T data) {
        if (current == null) return null;

        if (data.compareTo(current.data) == 0) {
            if (current.left == null && current.right == null) return null;
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (data.compareTo(current.data) < 0) {
            current.left = deleteRecursive(current.left, data);
            return current;
        }
        current.right = deleteRecursive(current.right, data);
        return current;
    }

    private T findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    public List<T> toSortedList() {
        List<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.data);
            inOrderTraversal(node.right, list);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}