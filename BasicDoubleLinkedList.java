import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
    protected Node head;
    protected Node tail;
    protected int size;

    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Inner Node class
    protected class Node {
        T data;
        Node prev;
        Node next;

        public Node(T data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }

    // Inner DoubleLinkedListIterator class
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node current;
        private int index;

        public DoubleLinkedListIterator() {
            current = head;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            index++;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = tail;
            } else {
                current = current.prev;
            }
            index--;
            return current.data;
        }

        // Rest of the ListIterator methods throwing UnsupportedOperationException
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }

    // Implement the Iterable interface by providing an iterator
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    public int getSize() {
        return size;
    }

    public void addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public T getFirst() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    public T getLast() {
        if (tail != null) {
            return tail.data;
        }
        return null;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public void remove(T target, Comparator<T> comparator) {
        Node current = head;
        while (current != null) {
            if (comparator.compare(current.data, target) == 0) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
            }
            current = current.next;
        }
    }

    public T retrieveFirstElement() {
        if (head != null) {
            T data = head.data;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return data;
        }
        return null;
    }

    public T retrieveLastElement() {
        if (tail != null) {
            T data = tail.data;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
            return data;
        }
        return null;
    }
}
