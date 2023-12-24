import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day23 {

    static HashSet<Integer> pathsLenghts = new HashSet<>();

    enum Direction {
        UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGHT (0, 1);

        int deltaRow, deltaCol;

        Direction(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }

        public static Direction getFromChar(char c) {
            switch (c) {
                case 'v':
                    return DOWN;
                case '^':
                    return UP;
                case '<':
                    return LEFT;
                case '>':
                    return RIGHT;
            }
            return null;
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

        Pos[][] map = new Pos[matrix.size()][matrix.get(0).size()];

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
        Pos[][] map = parseInput();
        Pos start = findStart(map);
        Pos end = findEnd(map);
        HashSet<Pos> visited = new HashSet<>();
        findLongestHike(map, start, end, visited);
        System.out.println(pathsLenghts.stream().max((a, b) -> a - b));
    }

    private static Pos findStart(Pos[][] map) {
        for (Pos pos : map[0]) {
            if (pos.c == '.') {
                return pos;
            }
        }
        return null;
    }

    private static Pos findEnd(Pos[][] map) {
        for (Pos pos : map[map.length - 1]) {
            if (pos.c == '.') {
                return pos;
            }
        }
        return null;
    }

    private static HashSet<Pos> findLongestHike(Pos[][] map, Pos curr, Pos end, HashSet<Pos> visited) {
       // System.out.println("Currently on " + curr + " (" + visited.size() + ")");

        if (curr.c == '#')
            return visited;

        if (visited.contains(curr)) {
            return visited;
        }
        visited.add(curr);

        if (curr.equals(end))
            return visited;

        int max = 0;
        for (Direction d : Direction.values()) {
            Direction availableDirection = Direction.getFromChar(curr.c);
            if (availableDirection != null && availableDirection != d) {
                continue;
            }
            int newRow = curr.row + d.deltaRow;
            int newCol = curr.col + d.deltaCol;

            HashSet<Pos> nextVisited = new HashSet<>(visited);

            if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                HashSet<Pos> result = findLongestHike(map, new Pos(newRow, newCol, map[newRow][newCol].c), end,
                        nextVisited);
                if (result.contains(end)) {
                    System.out.println("Il Percorso arriva in fondo! " + visited.size());
                    pathsLenghts.add(visited.size());
                }
            }
        }
        return visited;

    }

}
