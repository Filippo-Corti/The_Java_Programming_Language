import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Day21bis {

    record Point(long x, long y) {

        @Override
        public String toString() {
            return "Point [" + x + ", " + y + "]";
        }

    }

    enum Direction {
        UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGH(0, 1);

        int deltaRow, deltaCol;

        Direction(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }
    }

    record Pos(int row, int col, char c) {

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

    }

    public static Pos[][] parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Pos>> matrix = new ArrayList<>();

        int r = 0;
        while (s.hasNextLine()) {
            ArrayList<Pos> row = new ArrayList<>();
            String in = s.nextLine();
            for (int c = 0; c < in.length(); c++) {
                row.add(new Pos(r, c, in.charAt(c)));
            }
            r++;
            matrix.add(row);
        }

        int rows = matrix.size();
        int cols = matrix.get(0).size();

        Pos[][] map = new Pos[rows][cols];

        r = 0;
        for (ArrayList<Pos> row : matrix) {
            int c = 0;
            for (Pos pos : row) {
                map[r][c] = pos;
                c++;
            }
            r++;
        }

        return map;

    }

    public static void main(String[] args) {
        Point[] values = new Point[3];
        for (int i = 0; i < 3; i++) {
            Pos[][] map = parseInput();
            HashSet<Pos> reached1 = takeSteps(map, 65 + 131 * i);
            int res1 = printAndCount(map, reached1);
            System.out.println(res1);
            values[i] = new Point(65 + 131 * i, res1);
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        System.out.println(evalWithLagrange(values, 26501365L));

    }

    private static BigInteger evalWithLagrange(Day21bis.Point[] values, long x) {
        BigInteger sum = new BigInteger("0");

        for (int j = 0; j < values.length; j++) {
            BigInteger prod = new BigInteger("1");
            for (int k = 0; k < values.length; k++) {
                if (k != j) {
                    prod = prod.multiply(new BigInteger("" + (x - values[k].x) / (values[j].x - values[k].x)));
                }
            }
            sum = sum.add(prod.multiply(new BigInteger("" + values[j].y)));
        }

        return sum;
    }

    private static HashSet<Pos> instantCount(Pos[][] map, int stepsCount) {
        HashSet<Pos> reached = new HashSet<>();
        Pos start = findStart(map);
        int c = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int stepsFromStart = stepsBetween(start, new Pos(i, j, map[i][j].c));
                if (stepsFromStart <= stepsCount && map[i][j].c != '#' && stepsFromStart % 2 == stepsCount % 2) {
                    c++;
                    reached.add(new Pos(i, j, map[i][j].c));
                }
            }
        }
        return reached;
    }

    private static int stepsBetween(Pos start, Pos pos) {
        int deltaRow = Math.abs(pos.row - start.row);
        int deltaCol = Math.abs(pos.col - start.col);

        int deltaMin = (deltaRow < deltaCol) ? deltaRow : deltaCol;
        int deltaMax = (deltaRow > deltaCol) ? deltaRow : deltaCol;

        return deltaMin * 2 + (deltaMax - deltaMin);
    }

    private static int printAndCount(Pos[][] map, HashSet<Pos> reached) {
        /*
         * int count = 0;
         * for (int i = 0; i < map.length; i++) {
         * for (int j = 0; j < map[0].length; j++) {
         * if (reached.contains(new Pos(i, j, ' '))) {
         * count++;
         * System.out.print("O");
         * // System.out.println(Math.sqrt(Math.pow(i - start.row, 2.) + Math.pow(j -
         * // start.col, 2.)));
         * } else
         * System.out.print(map[i][j].c);
         * }
         * System.out.println();
         * }
         */
        return reached.size();
    }

    private static HashSet<Pos> takeSteps(Pos[][] map, long stepsCount) {

        int rows = map.length;
        int cols = map[0].length;

        Pos start = findStart(map);

        HashSet<Pos> reached = new HashSet<>();

        reached.add(start);

        for (long i = 0; i < stepsCount; i++) {
            HashSet<Pos> newReached = new HashSet<>();
            Iterator<Pos> iterator = reached.iterator();
            while (iterator.hasNext()) {
                Pos curr = iterator.next();
                iterator.remove();
                for (Direction dir : Direction.values()) {
                    int newRow = curr.row + dir.deltaRow;
                    int newCol = curr.col + dir.deltaCol;
                    char newC = map[((newRow + rows) % rows + rows) % rows][((newCol + cols) % cols + cols) % cols].c;
                    if (newC != '#')
                        newReached.add(new Pos(newRow, newCol, newC));
                }
            }
            reached = new HashSet<>(newReached);
        }

        return reached;

    }

    private static Pos findStart(Pos[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].c == 'S')
                    return map[i][j];
            }
        }
        return null;
    }

}