package singlelinkedlist;

import node.Node;

/**
 * Class "SingleLinkedList"
 *
 * @author Ousookie
 * @version 1.0
 */
public class SingleLinkedList {
    
    private static int size;
    private static Node<?> headNode;
    private static Node<?> tailNode;

    /**
     * @param value: value of new node
     */
    public static <T> void addNode(T value) {
        Node<T> node = new Node<>(value, null);
        if (size == 0) {
            headNode = node;
            tailNode = headNode;
        } else {
            headNode.setNextNode(node);
            headNode = tailNode;
            headNode = node;
        }
        size++;
    }

    /**
     * @param index: desired index for inserting new node
     * @param node:  insert node
     */
    public static <T> void insertNode(int index, Node<T> node) {
        Node<T> currentNode = (Node<T>) tailNode;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNextNode();
        }
        Node<T> temp = currentNode.getNextNode();
        currentNode.setNextNode(node);
        node.setNextNode(temp);
    }

    /**
     * @param index: desired node index
     * @return node with desired index
     */
    public static <T> Node<T> getNode(int index) {
        Node<T> currentNode = (Node<T>) tailNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    /**
     * Display all existing nodes.
     */
    public static <T> void showNodes() {
        Node<T> currentNode = (Node<T>) tailNode;
        while (currentNode != null) {
            System.out.println(currentNode.getValue() + " ");
            currentNode = currentNode.getNextNode();
        }
    }

    /**
     * @param index: desired node index for remove
     */
    public static <T> void popNode(int index) {
        if (index == 0) {
            tailNode = tailNode.getNextNode();
        }
        Node<T> currentNode = (Node<T>) tailNode;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNextNode();
        }
        currentNode.setNextNode(currentNode.getNextNode().getNextNode());
    }

    /**
     * @return count of all existing nodes
     */
    public static int getSize() {
        return size;
    }

    /**
     * @return last added node
     */
    public static <T> Node<T> getHeadNode() {
        return (Node<T>) headNode;
    }

    /**
     * @return first added node
     */
    public static <T> Node<T> getTailNode() {
        return (Node<T>) tailNode;
    }

}
