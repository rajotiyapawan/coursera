import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) { // first item
            first = new Node(item);
            last = first;
            size++;
        }
        else {
            Node t = new Node(item);
            t.next = first;
            first.prev = t;
            first = t;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) { // first item
            last = new Node(item);
            first = last;
            size++;
        }
        else {
            Node t = new Node(item);
            t.prev = last;
            last.next = t;
            last = t;
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<String> dq = new Deque<>();
        // while (!StdIn.isEmpty()) {
        //     String str = StdIn.readString();
        //     if (str.contains("-")) {
        //         StdOut.println("Removed : " + dq.removeFirst());
        //     }
        //     else if (str.contains("_")) {
        //         StdOut.println("Removed : " + dq.removeLast());
        //     }
        //     else if (str.contains("+")) {
        //         dq.addLast(str);
        //         StdOut.println("Added : " + str);
        //     }
        //     else if (str.contains("i")) {
        //         Iterator<String> it = dq.iterator();
        //         while (it.hasNext()) {
        //             StdOut.println(it.next());
        //         }
        //     }
        //     else {
        //         dq.addFirst(str);
        //         StdOut.println("Added : " + str);
        //     }
        // }
    }

}
