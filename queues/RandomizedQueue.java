import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
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
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node t = new Node(item);
        if (size == 0) {
            head = t;
            tail = head;
            size++;
        }
        else if (size == 1) {
            head.next = t;
            tail = head.next;
            size++;
        }
        else {
            tail.next = t;
            tail = tail.next;
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            Item item0 = head.item;
            head = null;
            size--;
            return item0;
        }
        int index = StdRandom.uniformInt(0, size);
        Node temp = head;
        for (int j = 1; j < index - 1; j++) {
            temp = temp.next;
        }
        Item item = temp.next.item;
        temp.next = temp.next.next;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(0, size);
        Node temp = head;
        for (int j = 0; j < index; j++) {
            temp = temp.next;
        }
        Item item = temp.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int[] perm = StdRandom.permutation(size);
            private int i = 0;

            public boolean hasNext() {
                return i < size;
            }

            public Item next() {
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                Node temp = head;
                for (int j = 0; j < perm[i]; j++) {
                    temp = temp.next;
                }
                i++;
                return temp.item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        // RandomizedQueue<String> dq = new RandomizedQueue<>();
        // while (!StdIn.isEmpty()) {
        //     String str = StdIn.readString();
        //     if (str.contains("-")) {
        //         StdOut.println("Removed : " + dq.dequeue());
        //         StdOut.println("Size : " + dq.size());
        //         StdOut.println("Empty : " + dq.isEmpty());
        //     }
        //     else if (str.contains("+")) {
        //         StdOut.println("Sample : " + dq.sample());
        //     }
        //     else if (str.contains("i")) {
        //         StdOut.println("Size : " + dq.size());
        //         Iterator<String> it = dq.iterator();
        //         while (it.hasNext()) {
        //             StdOut.println(it.next());
        //         }
        //     }
        //     else {
        //         dq.enqueue(str);
        //         StdOut.println("Added : " + str);
        //     }
        // }

        // Iterator<String> it = dq.iterator();
        // while (it.hasNext()) {
        //     StdOut.println(it.next());
        // }
        // it.remove();
    }

}