import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private int segments = 0;
    private Point origin;

    public FastCollinearPoints(Point[] points) {
        validatePoints(points);
        int n = points.length;
        for (int i = 0; i < n; i++) {
            origin = points[i];
            // get the array sorted
            Point[] pointArr = points.clone();
            Arrays.sort(pointArr, origin.slopeOrder());
            // mergeSort(pointArr, 0, n - 1);
            ArrayList<Point> cp = new ArrayList<>();
            double slope = 0.0;
            Point min = pointArr[0];
            Point max = pointArr[0];
            for (int j = 0; j < n; j++) {
                Point q = pointArr[j];
                double currentSlope = origin.slopeTo(q);
                if (j == 0 || slope != currentSlope) {
                    if (cp.size() >= 3) {
                        addSegment(new LineSegment(min, max));
                    }
                    cp.clear();
                    min = pointArr[0];
                    max = pointArr[0];
                }

                cp.add(q);
                slope = currentSlope;
                if (min.compareTo(q) > 0) min = q;
                if (max.compareTo(q) < 0) max = q;
                // Point r = points[j + 1];
                // Point s = points[j + 2];
                // double sl1 = origin.slopeTo(q);
                // double sl2 = origin.slopeTo(r);
                // double sl3 = origin.slopeTo(s);
                // if (sl1 == sl2 && sl2 == sl3) { // are collinear
                //     Point min = origin;
                //     Point max = origin;
                //     if (min.compareTo(q) > 0) min = q;
                //     if (min.compareTo(r) > 0) min = r;
                //     if (min.compareTo(s) > 0) min = s;
                //     if (max.compareTo(q) < 0) max = q;
                //     if (max.compareTo(r) < 0) max = r;
                //     if (max.compareTo(s) < 0) max = s;
                //     addSegment(new LineSegment(min, max));
                // }
            }
            if (cp.size() >= 3) {
                addSegment(new LineSegment(min, max));
            }
        }
    }

    private void validatePoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {

                if (points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException();
                }
                if (i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private void addSegment(LineSegment ls) {
        boolean flag = false;
        for (LineSegment seg : lineSegments) {
            if (seg.toString().equals(ls.toString())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            segments++;
            lineSegments.add(ls);
        }
    }

    // private int compareSlope(Point x, Point y) {
    //     return origin.slopeOrder().compare(x, y);
    // }

    // private void mergeArr(Point[] points, int start, int mid, int end) {
    //     int i = start;
    //     int j = mid + 1;
    //     if (compareSlope(points[mid], points[mid + 1]) <= 0) {
    //         return; // already merged
    //     }
    //     while (i <= mid && j <= end) {
    //         if (compareSlope(points[i], points[j]) > 0) {
    //             Point temp = points[j];
    //             int k = j;
    //             while (k != i) {
    //                 points[k] = points[k - 1];
    //                 k--;
    //             }
    //             points[i] = temp;
    //             i++;
    //             j++;
    //         }
    //         else {
    //             i++;
    //         }
    //     }
    // }

    // private void mergeSort(Point[] points, int start, int end) {
    //     int n = end - start;
    //     if (n < 1) { // only one element
    //         return;
    //     }
    //     else if (n == 1) { // only 2 elements
    //         if (compareSlope(points[start], points[end]) > 0) {
    //             Point temp = points[end];
    //             points[end] = points[start];
    //             points[start] = temp;
    //         }
    //     }
    //     else {
    //         int mid = (start + end) / 2;
    //         mergeSort(points, start, mid);
    //         mergeSort(points, mid + 1, end);
    //         mergeArr(points, start, mid, end);
    //     }
    // }

    public int numberOfSegments() {
        return segments;
    }


    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[segments]);
    }

    public static void main(String[] args) {
        // int n = StdIn.readInt();
        // Point[] points = new Point[n];
        // int i = 0;
        // while (i < n) {
        //     int x = StdIn.readInt();
        //     int y = StdIn.readInt();
        //     points[i] = (new Point(x, y));
        //     i++;
        // }
        // FastCollinearPoints fcp = new FastCollinearPoints(points);
        // StdOut.print(Arrays.toString(points));

        // read the n points from a file
        // int n = StdIn.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = StdIn.readInt();
        //     int y = StdIn.readInt();
        //     points[i] = new Point(x, y);
        // }
        //
        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();
        //
        // // print and draw the line segments
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }
}
