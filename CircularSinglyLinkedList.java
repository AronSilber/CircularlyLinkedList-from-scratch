import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Aron Silberwasser
 * @version 1.0
 * @userid asilberwasser3
 * @GTID 903683147
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {

        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index cannot be <0 or >size");
        }

        if (size == 0) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else if (index == 0) {
            CircularSinglyLinkedListNode<T> newNode =
                    new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());

            head.setData(data);
            head.setNext(newNode);
        } else if (index == size) {
            CircularSinglyLinkedListNode<T> newNode =
                    new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());

            head.setData(data);
            head.setNext(newNode);

            head = newNode;
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            CircularSinglyLinkedListNode<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                newNode.setNext(current.getNext());
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        size++;
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (size == 0) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode =
                    new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());

            head.setData(data);
            head.setNext(newNode);
        }

        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (size == 0) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode =
                    new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());

            head.setData(data);
            head.setNext(newNode);

            head = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index cannot be <0 or >size");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }

        if (index == 0) {
            T removedData = head.getData();

            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());

            size--;

            return removedData;
        } else {
            CircularSinglyLinkedListNode<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            T removedData = current.getNext().getData();
            current.setNext(current.getNext().getNext());

            size--;

            return removedData;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {

        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }

        T removedData = head.getData();

        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());

        size--;

        return removedData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {

        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }

        CircularSinglyLinkedListNode<T> current = head;

        for (int i = 0; i < size - 2; i++) {
            current = current.getNext();
        }
        T removedData = current.getNext().getData();
        current.setNext(current.getNext().getNext());

        size--;

        return removedData;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index cannot be <0 or >size");
        }

        if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {

        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        CircularSinglyLinkedListNode<T> current = head;

        while (!current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        T removedData = current.getNext().getData();
        current.setNext(current.getNext().getNext());

        size--;

        return removedData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {

        T[] arr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> current = head;

        for (int i = 0; i < size; i++) {
            arr[i] = current.getData();
            current = current.getNext();
        }

        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
