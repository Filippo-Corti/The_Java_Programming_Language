import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18bis {

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class Direction {
        long dx;
        long dy;
        String color;

        public Direction(long dx, long dy, String color) {
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + dx + ", " + dy + ")";
        }

    }

    public static ArrayList<Direction> parseInput1() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Direction> directions = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            int dValue = Integer.parseInt(in[1]);
            int dx = 0, dy = 0;
            switch (in[0]) {
                case "R":
                    dx = dValue;
                    break;
                case "L":
                    dx = -dValue;
                    break;
                case "U":
                    dy = -dValue;
                    break;
                case "D":
                    dy = dValue;
                    break;
            }
            directions.add(new Direction(dx, dy, ""));
        }

        return directions;
    }

    public static ArrayList<Direction> parseInput2() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        ArrayList<Direction> directions = new ArrayList<>();

        while (s.hasNextLine()) {
            String in = s.nextLine().split(" ")[2];
            long dValue = Long.parseLong(in.substring(2, 7), 16);
            long dx = 0, dy = 0;
            switch (in.charAt(7)) {
                case '0':
                    dx = dValue;
                    break;
                case '2':
                    dx = -dValue;
                    break;
                case '3':
                    dy = -dValue;
                    break;
                case '1':
                    dy = dValue;
                    break;
            }
            directions.add(new Direction(dx, dy, ""));
        }

        return directions;
    }


    public static void main(String[] args) {
        ArrayList<Direction> directions = parseInput2();
        Data data = getVertices(directions);
        System.out.println("Gauss: " + areaWithGauss(data));
    }

    public static class Data {
        ArrayList<Point> vertices;
        long pointsOnPerimeter;
    }

    private static Data getVertices(ArrayList<Direction> directions) {
        ArrayList<Point> vertices = new ArrayList<>();
        long currX = 0, currY = 0;

        long pointsOnPerimeter = 0;

        vertices.add(new Point(currX, currY));

        for (Direction direction : directions) {
            currX += direction.dx;
            currY += direction.dy;

            vertices.add(new Point(currX, currY));
            pointsOnPerimeter += Math.abs(direction.dx) + Math.abs(direction.dy);
        }

        Data data = new Data();
        data.pointsOnPerimeter = pointsOnPerimeter;
        data.vertices = vertices;

       // System.out.println(vertices);

        return data;
    }

    private static long areaWithGauss(Data data) {
        long area = 0;
        for (int i = 1; i < data.vertices.size(); i++) {
            area += data.vertices.get(i).x * data.vertices.get(i - 1).y
                    - data.vertices.get(i - 1).x * data.vertices.get(i).y;
        }
        long n = data.vertices.size();
        return Math.abs(area) / 2 + data.pointsOnPerimeter / 2 + 1;
    }

}
