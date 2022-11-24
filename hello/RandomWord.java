import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String result = "";
        double i = 1.0;
        while (!StdIn.isEmpty()) {
            double p = 1.0 / i;
            String temp = StdIn.readString();
            if (StdRandom.bernoulli(p)) {
                result = temp;
            }
            i++;
        }
        StdOut.println(result);
    }
}
