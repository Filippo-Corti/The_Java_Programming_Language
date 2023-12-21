import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Day21 {

    enum Direction {
        UP(-1, 0), LEFT(0, -1), DOWN (1, 0), RIGH (0, 1);

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
        HashSet<Pos> reached = takeSteps(map, 64);
        System.out.println(printAndCount(map, reached));
    }


    private static int printAndCount(Pos[][] map, HashSet<Pos> reached) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (reached.contains(new Pos(i, j, ' '))) {
                    count++;
                    System.out.print("O");
                }
                else
                    System.out.print(map[i][j].c);
            }
            System.out.println();
        }
        return count;
    }

    private static HashSet<Pos> takeSteps(Pos[][] map, long stepsCount) {

        Pos start = findStart(map);

        HashSet<Pos> reached = new HashSet<>();

        reached.add(start);

        for (long i = 0; i < stepsCount; i++) {
            HashSet<Pos> newReached = new HashSet<>();
            Iterator<Pos> iterator = reached.iterator();
            while(iterator.hasNext()) {
                Pos curr = iterator.next();
                iterator.remove();
                for (Direction dir : Direction.values()) {
                    int newRow = curr.row + dir.deltaRow;
                    int newCol = curr.col + dir.deltaCol;
                    if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map[0].length)
                        continue;
                    char newC = map[newRow][newCol].c;
                    if (newC != '#')
                    newReached.add(new Pos(newRow, newCol, newC));
                }
            }
            reached = new HashSet<>(newReached);
            System.out.println(i);
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