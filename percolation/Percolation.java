import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // Grid
    private final boolean[][] grid;
    private int gridSize;

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF df;
    private int virtualTop;
    private int virtualBottom;

    // todo keep track of open sites
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // check if n>0
        if (n <= 0) {
            throw new IllegalArgumentException("n should > 0");
        }

        // todo initialise the variables
        grid = new boolean[n][n];
        gridSize = n;
        int totalNodes = n * n;
        openSites = 0;

        uf = new WeightedQuickUnionUF(totalNodes + 2); // todo include virtual top and bottom
        // todo include only virtual bottom for corner cases with already percolated
        // every bottom site will come full for uf for percolated grid
        df = new WeightedQuickUnionUF(totalNodes + 1);
        virtualTop = totalNodes;
        virtualBottom = totalNodes + 1;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validArgument(row, col);

        // todo if already open then return
        if (isOpen(row, col)) {
            return;
        }
        int r = row - 1;
        int c = col - 1;
        // todo update grid
        grid[r][c] = true;
        openSites++;

        // todo check if its in top row then connect to virtual top
        if (r == 0) {
            uf.union(getPos(row, col), virtualTop);
            df.union(getPos(row, col), virtualTop);
        }

        // todo check if its in bottom row then connect to virtual bottom
        if (row == gridSize) {
            uf.union(getPos(row, col), virtualBottom);
        }

        // todo check if there are open sites adjacent to it
        connectAdjSites(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validArgument(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    // todo site is connected to the top
    // check from df since we don't need virtual bottom to check full
    public boolean isFull(int row, int col) {
        validArgument(row, col);
        return df.find(getPos(row, col)) == df.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    // todo virtual top and virtual bottom are connected
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // todo check for wrong input
    // todo grid starts from zero while input from 1
    private void validArgument(int row, int col) {
        if (!isValidSite(row, col)) throw new IllegalArgumentException("index is out of bound r,c ");
    }

    private boolean isValidSite(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        return r >= 0 && c >= 0 && r < gridSize && c < gridSize;
    }

    // todo find position on 1-d grid
    private int getPos(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        return gridSize * r + c;
    }

    private void connectAdjSites(int row, int col) {
        int pos = getPos(row, col);
        // left
        if (isValidSite(row, col-1)) {
            if (isOpen(row, col - 1)) {
                uf.union(pos, getPos(row, col-1));
                df.union(pos, getPos(row, col-1));
            }
        }
        // right
        if (isValidSite(row, col+1)) {
            if (isOpen(row, col + 1)) {
                uf.union(pos, getPos(row, col+1));
                df.union(pos, getPos(row, col+1));
            }
        }
        // top
        if (isValidSite(row-1, col)) {
            if (isOpen(row - 1, col)) {
                uf.union(pos, getPos(row - 1, col));
                df.union(pos, getPos(row - 1, col));
            }
        }
        // bottom
        if (isValidSite(row+1, col)) {
            if (isOpen(row + 1, col)) {
                uf.union(pos, getPos(row + 1, col));
                df.union(pos, getPos(row + 1, col));
            }
        }
    }

    public static void main(String[] args) {

    }
}
