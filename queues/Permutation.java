import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> dq = new RandomizedQueue<>();
        // int i = 0;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            dq.enqueue(str);
            // i++;
        }
        while (k > 0) {
            StdOut.println(dq.dequeue());
            k--;
        }
    }
}