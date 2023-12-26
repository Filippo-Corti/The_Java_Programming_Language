import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day24bis {

    // static double MIN = 200000000000000.;
    // static double MAX = 400000000000000.;
    static double MIN = 7.;
    static double MAX = 27.;

    record Speed(double dx, double dy) {
    }

    record Hailstone(StraightLine direction, Point start, Speed speed) {

        public boolean isReachable(Point intersection) {
            if (intersection.x < start.x && speed.dx > 0)
                return false;
            if (intersection.y < start.y && speed.dy > 0)
                return false;
            if (intersection.x > start.x && speed.dx < 0)
                return false;
            if (intersection.y > start.y && speed.dy < 0)
                return false;

            return true;

        }
    }

    record Point(double x, double y) {
    }

    static class StraightLine {
        // y = mx + q
        double m, q;

        public StraightLine(double m, double q) {
            this.m = m;
            this.q = q;
        }

        @Override
        public String toString() {
            return "y = " + m + "x + " + q;
        }

    }

    public static ArrayList<Hailstone> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Hailstone> hailstones = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split("[@,]");
            long x1 = Long.parseLong(in[0].trim());
            long x2 = x1 + Long.parseLong(in[3].trim());
            long y1 = Long.parseLong(in[1].trim());
            long y2 = y1 + Long.parseLong(in[4].trim());

            hailstones.add(new Hailstone(findStraightLineFromTwoPoints(x1, y1, x2, y2), new Point(x1, y1),
                    new Speed(Long.parseLong(in[3].trim()), Long.parseLong(in[4].trim()))));
        }

        return hailstones;

    }

    private static StraightLine findStraightLineFromTwoPoints(long b, long a, long d, long c) {
        // Solves:
        // a = mb + q
        // c = md + q
        double m = ((double) (c - a)) / (d - b);
        double q = a - m * b;
        return new StraightLine(m, q);
    }

    public static void main(String[] args) {
        ArrayList<Hailstone> hailstones = parseInput();
        System.out.println(hailstones.toString().replace("Hail", "\nHail"));
        int c = 0;
        for (int i = 0; i < hailstones.size(); i++) {
            for (int j = i + 1; j < hailstones.size(); j++) {
                Hailstone h1 = hailstones.get(i);
                Hailstone h2 = hailstones.get(j);
                Point intersection = findIntersection(h1.direction, h2.direction);
                if (j == hailstones.size() - 1)
                    System.out.println(h1 + ", " + h2 + "\n" + intersection + "\n\n");
                if (intersection.x >= MIN && intersection.x <= MAX && intersection.y >= MIN && intersection.y <= MAX
                        && h1.isReachable(intersection) && h2.isReachable(intersection)) {
                    c++;
                }
            }
        }
        System.out.println(c);
    }

    private static Point findIntersection(StraightLine line1, StraightLine line2) {
        // Solves:
        // y = (line1.m)x + line1.q
        // y = (line2.m)x + line2.q
        double x = (line2.q - line1.q) / (line1.m - line2.m);
        double y = line1.m * x + line1.q;
        return new Point(x, y);
    }

}