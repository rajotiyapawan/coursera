import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private int segments = 0;

    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[m];
                        double sl1 = p.slopeTo(q);
                        double sl2 = p.slopeTo(r);
                        double sl3 = p.slopeTo(s);
                        if (sl1 == sl2 && sl2 == sl3) {
                            segments++;
                            Point min = p;
                            Point max = p;
                            if (min.compareTo(q) > 0) min = q;
                            if (min.compareTo(r) > 0) min = r;
                            if (min.compareTo(s) > 0) min = s;
                            if (max.compareTo(q) < 0) max = q;
                            if (max.compareTo(r) < 0) max = r;
                            if (max.compareTo(s) < 0) max = s;
                            lineSegments.add(new LineSegment(min, max));
                        }
                    }
                }
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

    public int numberOfSegments() {
        return segments;
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[segments]);
    }

    public static void main(String[] args) {
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
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }
}
