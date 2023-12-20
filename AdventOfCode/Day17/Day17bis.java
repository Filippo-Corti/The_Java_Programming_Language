import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day17bis {

    public static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pos other = (Pos) obj;
            if (row != other.row)
                return false;
            if (col != other.col)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Pos [row=" + row + ", col=" + col + "]";
        }

    }

    public static enum Direction {
        STILL(0, 0), UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

        int deltaRow;
        int deltaCol;

        Direction(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }

        public boolean equals(Direction other) {
            if (deltaRow != other.deltaRow)
                return false;
            if (deltaCol != other.deltaCol)
                return false;
            return true;
        }

        public Direction invert() {
            switch (this) {
                case UP:
                    return DOWN;
                case DOWN:
                    return UP;
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
            }
            return null;
        }

        @Override
        public String toString() {
            return "Direction [deltaRow=" + deltaRow + ", deltaCol=" + deltaCol + "]";
        }

    }

    public static class Tuple implements Comparable<Tuple> {

        Pos position;
        Direction direction;
        int straightLineCount;
        long distanceFromSource;

        public Tuple(int row, int col, Direction dir, int straightLineCount,
                long distanceFromSource) {
            this.position = new Pos(row, col);
            this.direction = dir;
            this.straightLineCount = straightLineCount;
            this.distanceFromSource = distanceFromSource;
        }

        @Override
        public int compareTo(Tuple o) {
            int res = Long.compare(distanceFromSource, o.distanceFromSource);
            if (res == 0)
                res = Integer.compare(o.position.row + o.position.col, position.row + position.col);
            return res;
        }

        @Override
        public String toString() {
            return "Tuple [position=" + position + ", direction=" + direction + ", straightLineCount="
                    + straightLineCount + "]";
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, direction, straightLineCount);
        }

    }

    public static ArrayList<ArrayList<Integer>> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        while (s.hasNextLine()) {
            ArrayList<Integer> row = new ArrayList<>();
            String in = s.nextLine();
            for (int i = 0; i < in.length(); i++) {
                row.add(Integer.parseInt(in.charAt(i) + ""));
            }
            matrix.add(row);
        }

        return matrix;

    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> map = parseInput();

        int[][] m = toArray(map);

        long res = shortestPathToEnd(m);

        System.out.println(res);

    }

    private static int[][] toArray(ArrayList<ArrayList<Integer>> map) {
        int[][] m = new int[map.size()][map.get(0).size()];
        int r = 0;
        for (ArrayList<Integer> row : map) {
            int c = 0;
            for (int i : row) {
                m[r][c] = i;
                c++;
            }
            r++;
        }
        return m;
    }

    private static long shortestPathToEnd(int[][] map) {

        Direction directions[] = Direction.values();

        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();

        Tuple t = new Tuple(0, 0, Direction.STILL, 0, 0);
        pq.add(t);

        while (!pq.isEmpty()) {

            Tuple curr = pq.remove();
            // System.out.println(curr.distanceFromSource);
           // System.out.println(pq.size());

            if ((curr.position.row == map.length - 1 && curr.position.col == map[0].length - 1) && curr.straightLineCount >= 4)
                return curr.distanceFromSource;

            if (visited.contains(curr.toString()))
                continue;

            visited.add(curr.toString());

            // Try same direction (if you can)
            if (curr.straightLineCount < 10 && curr.direction != directions[0]) {
                int newRow = curr.position.row + curr.direction.deltaRow;
                int newCol = curr.position.col + curr.direction.deltaCol;
                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                    pq.add(new Tuple(newRow, newCol, curr.direction, curr.straightLineCount + 1,
                            curr.distanceFromSource + map[newRow][newCol]));
                }
            }

            // Try all other direction (if you can)
            if (curr.straightLineCount >= 4 || curr.direction == directions[0]) {
                for (int i = 1; i < directions.length; i++) {
                    Direction d = directions[i];
                    if (curr.direction != d && curr.direction != d.invert()) {
                        // System.out.println(curr.direction + " - " + d);
                        int newRow = curr.position.row + d.deltaRow;
                        int newCol = curr.position.col + d.deltaCol;
                        if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                            pq.add(new Tuple(newRow, newCol, d, 1,
                                    curr.distanceFromSource + map[newRow][newCol]));
                        }
                    }
                }
            }
        }

        return -1;

    }

}