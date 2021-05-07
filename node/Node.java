package node;

/**
 * Class "Node"
 *
 * @author Ousookie
 * @version 1.0
 */
public class Node<T> {
    private T value;
    private Node<T> nextNode;

    /**
     * @param value:    value of node
     * @param nextNode: pointer to the next node
     */
    public Node(T value, Node<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    /**
     * @return value of current node
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value: desired value of current node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return next node
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    /**
     * @param nextNode : desired next node pointer
     */
    public void setNextNode(Node<?> nextNode) {
        this.nextNode = (Node<T>) nextNode;
    }

}
