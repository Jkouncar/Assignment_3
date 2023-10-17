import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> comparator) {
        super(); // Call the constructor of the parent class
        this.comparator = comparator;
    }

    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("addToFront is not supported in a sorted list.");
    }

    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("addToEnd is not supported in a sorted list.");
    }

    public void add(T data) {
        Node newNode = new Node(data);

        if (head == null || comparator.compare(data, head.data) <= 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (comparator.compare(data, tail.data) >= 0) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head;
            while (current != null && comparator.compare(data, current.data) > 0) {
                current = current.next;
            }
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
    }

    @Override
    public ListIterator<T> iterator() {
        return new SortedDoubleLinkedListIterator();
    }

    // Inner SortedDoubleLinkedListIterator class
    private class SortedDoubleLinkedListIterator implements ListIterator<T> {
        private Node current;
        private int index;

        public SortedDoubleLinkedListIterator() {
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
}
