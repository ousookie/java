package stack;

/**
 * @param <T>: desired type of the stack values
 * @author Dikanskiy Nikolay
 * @version 1.0
 */
public class Stack<T> {
    
    private static int stackSize;
    private T top;
    final private T[] values;

    /**
     * @return size of the stack
     */
    public static int getStackSize() {
        return stackSize;
    }

    /**
     * @param stackSize: desired stack size
     */
    private static void setStackSize(int stackSize) {
        Stack.stackSize = stackSize;
    }

    /**
     * @param stackSize: desired stack size
     */
    public Stack(int stackSize) {
        setStackSize(stackSize);
        this.values = (T[]) new Object[stackSize];
    }

    /**
     * @return value on the top of the stack
     */
    public T getTop() {
        return top;
    }

    /**
     * @return all stack values
     */
    public T[] getValues() {
        return values;
    }

    /**
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return getValues()[stackSize] == null;
    }

    /**
     * @return true if stack is full
     */
    public boolean isFull() {
        return getValues()[0] != null;
    }

    /**
     * Add new value on top of the stack.
     *
     * @param value: desired value on top of the stack.
     */
    public void pushFront(T value) {
        if (!isFull()) {
            values[stackSize - 1] = value;
            top = value;
            stackSize--;
        }

    }

    /**
     * Remove value on top of the stack.
     */
    public void popFront() {
        if (!isEmpty()) {
            values[stackSize] = null;
            top = values[stackSize + 1];
            stackSize++;
        }
    }

    /**
     * Shows all stack values.
     */
    public void displayValues() {
        for (T item : values) {
            System.out.println(item);
        }
    }

    /**
     * @param index: desired value index
     * @return desired value
     */
    public T getValue(int index) {
        return values[index];
    }
    
}

