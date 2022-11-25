import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percThres;
    private int tCases;
    private double confidence_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // todo check if trials > 0
        if (trials <= 0) throw new IllegalArgumentException("trials should be > 0");

        tCases = trials;
        percThres = new double[trials];

        for (int t = 0; t < tCases; t++) {
            Percolation perc = new Percolation(n);
            // todo open sites till it percolates
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                perc.open(row, col);
            }
            // todo collect data
            int openSites = perc.numberOfOpenSites();
            double thres = (double) openSites / (n * n);
            percThres[t] = thres;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percThres);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percThres);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - (confidence_95 * stddev()) / (Math.sqrt(tCases)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + (confidence_95 * stddev()) / (Math.sqrt(tCases)));
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int gridSize = Integer.parseInt(args[0]);
            int trialCount = Integer.parseInt(args[1]);

            PercolationStats ps = new PercolationStats(gridSize, trialCount);

            String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
            StdOut.println("mean                    = " + ps.mean());
            StdOut.println("stddev                  = " + ps.stddev());
            StdOut.println("95% confidence interval = [" + confidence +"]");
        }
    }
}
