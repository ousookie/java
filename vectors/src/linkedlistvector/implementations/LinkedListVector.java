package linkedlistvector.implementations;

import java.io.Serializable;

import static java.lang.Math.sqrt;

public class LinkedListVector implements Serializable {

    private double nodeValue;
    private LinkedListVector nextNode;
    private LinkedListVector prevNode;

    private static LinkedListVector headNode;
    private static LinkedListVector tailNode;
    private static LinkedListVector lastQueryNode;
    private static int size;
    private static int lastQueryNodeIndex;

    public LinkedListVector(double nodeValue, LinkedListVector nextNode, LinkedListVector prevNode) {
        this.nodeValue = nodeValue;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public double getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(double nodeValue) {
        this.nodeValue = nodeValue;
    }

    public LinkedListVector getNextNode() {
        return nextNode;
    }

    public void setNextNode(LinkedListVector nextNode) {
        this.nextNode = nextNode;
    }

    public LinkedListVector getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(LinkedListVector prevNode) {
        this.prevNode = prevNode;
    }

    public static int getSize() {
        return size;
    }

    public static LinkedListVector getLastQueryNode() {
        return lastQueryNode;
    }

    public static int getLastQueryNodeIndex() {
        return lastQueryNodeIndex;
    }

    public static LinkedListVector getHeadNode() {
        return headNode;
    }

    public static LinkedListVector getTailNode() {
        return tailNode;
    }

    public static double euclideanNorm() {
        double sum = 0;
        LinkedListVector node = headNode;
        for (int i = 1; i <= size; i++) {
            sum += fastPower(node.getNodeValue(), 2);
            node = node.nextNode;
        }
        return sqrt(sum);
    }

    public static double fastPower(double base, int exp) {
        if (exp == 0) return 1;
        if (exp % 2 == 0) return fastPower(base * base, exp / 2);
        return base * fastPower(base, exp - 1);
    }

    public static void addNode(double nodeValue) {
        LinkedListVector node = new LinkedListVector(nodeValue, null, null);
        if (size == 0) {
            headNode = node;
            tailNode = headNode;
        } else {
            LinkedListVector temp = tailNode;
            tailNode.nextNode = node;
            tailNode = node;
            tailNode.prevNode = temp;
            tailNode.nextNode = headNode;
            headNode.prevNode = tailNode;
        }
        size++;
    }

    public static void showNodes() {
        LinkedListVector curr = headNode;
        for (int i = 1; i <= size; i++) {
            System.out.print(curr.getNodeValue() + " ");
            curr = curr.nextNode;
        }
        System.out.println();
    }

    public static LinkedListVector getNode(int index) {
        double start = System.currentTimeMillis();
        LinkedListVector current = headNode;
        for (int i = 1; i < index; i++) {
            current = current.nextNode;
        }
        double end = System.currentTimeMillis();
        System.out.println("without optimization: " + (end - start) / 1000 + " s");
        return current;
    }

    public static LinkedListVector getNodeWithOptimization(int index) {
        double start = System.currentTimeMillis();
        if (index == 1) {
            return headNode;
        } else if (index == size) {
            return tailNode;
        }
        LinkedListVector node = new LinkedListVector(0, null, null);
        if (lastQueryNode == null) {
            LinkedListVector current = headNode;
            for (int i = 1; i < index; i++) {
                current = current.nextNode;
                lastQueryNodeIndex = index;
            }
            lastQueryNode = current;
            node = current;
        } else {
            if (index == lastQueryNodeIndex) {
                return lastQueryNode;
            } else if (index < (size / 2) && lastQueryNodeIndex > (size / 2)) {
                LinkedListVector current = lastQueryNode;
                for (int i = 0; i < size - (lastQueryNodeIndex - index); i++) {
                    current = current.nextNode;
                }
                lastQueryNode = current;
                node = current;
            } else if (index > (size / 2) && lastQueryNodeIndex < (size / 2)) {
                LinkedListVector current = lastQueryNode;
                for (int i = 0; i < size - (index - lastQueryNodeIndex); i++) {
                    current = current.prevNode;
                }
                lastQueryNode = current;
                node = current;
            } else if (index < (size / 2) && lastQueryNodeIndex < (size / 2)) {
                LinkedListVector current = lastQueryNode;
                if (index > lastQueryNodeIndex) {
                    for (int i = 0; i < index - lastQueryNodeIndex; i++) {
                        current = current.nextNode;
                    }
                } else {
                    for (int i = 0; i < lastQueryNodeIndex - index; i++) {
                        current = current.prevNode;
                    }
                }
                lastQueryNode = current;
                node = current;
            } else if (index > (size / 2) && lastQueryNodeIndex > (size / 2)) {
                LinkedListVector current = lastQueryNode;
                if (index > lastQueryNodeIndex) {
                    for (int i = 0; i < index - lastQueryNodeIndex; i++) {
                        current = current.nextNode;
                    }
                } else {
                    for (int i = 0; i < lastQueryNodeIndex - index; i++) {
                        current = current.prevNode;
                    }
                }
                lastQueryNode = current;
                node = current;
            }
        }
        double end = System.currentTimeMillis();
        System.out.println("with optimization " + (end - start) / 1000 + " s");
        return node;
    }

    public static void popNode(int index) {
        LinkedListVector node = headNode;
        if (index == 1) {
            headNode = node.nextNode;
        } else {
            for (int i = 1; i < index; i++) {
                node = node.nextNode;
            }
        }
        node.nextNode.prevNode = node.prevNode;
        node.prevNode.nextNode = node.nextNode;
        size--;
    }

}





