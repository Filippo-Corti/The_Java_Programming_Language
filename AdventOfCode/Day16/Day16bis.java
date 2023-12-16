import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day16bis {

    static ArrayList<String> cache = new ArrayList<>();
    static HashMap<Cell, Integer> visitedCells = new HashMap<>();

    static class Cell {
        int r, c;
        char value;

        public Cell(int r, int c, char value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + value + " -> " + r + ", " + c + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + r;
            result = prime * result + c;
            result = prime * result + value;
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
            Cell other = (Cell) obj;
            if (r != other.r)
                return false;
            if (c != other.c)
                return false;
            if (value != other.value)
                return false;
            return true;
        }

    }

    public static String strinfiyState(Cell current, int[] dir) {
        return current.toString() + " - " + dir[0] + dir[1];
    }

    public static ArrayList<ArrayList<Cell>> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

        int rowIndex = 1;
        while (s.hasNextLine()) {
            String in = "X" + s.nextLine() + "X";
            ArrayList<Cell> row = new ArrayList<>();
            for (int i = 0; i < in.length(); i++) {
                row.add(new Cell(rowIndex, i, in.charAt(i)));
            }
            grid.add(row);
            rowIndex++;
        }

        // Add border
        ArrayList<Cell> borderTop = new ArrayList<>();
        for (int i = 0; i < grid.get(0).size(); i++) {
            borderTop.add(new Cell(0, i, 'X'));
        }
        ArrayList<Cell> borderBottom = new ArrayList<>();
        for (int i = 0; i < grid.get(0).size(); i++) {
            borderBottom.add(new Cell(grid.size() - 1, i, 'X'));
        }

        grid.add(0, borderTop);
        grid.add(borderBottom);

        return grid;
    }

    public static void print(ArrayList<ArrayList<Cell>> platform) {
        for (ArrayList<Cell> row : platform) {
            for (Cell ch : row) {
                System.out.print(ch.value);
            }
            System.out.println();
        }
    }

    public static void castRay(ArrayList<ArrayList<Cell>> grid, Cell current, int[] dir) {

        String stringified = strinfiyState(current, dir);

        if (cache.contains(stringified)) {
            // System.out.println("That's a loop!");
            return;
        }

        cache.add(stringified);

        while (true) {
            // System.out.println("Currently on " + current);
            if (current.value == 'X') {
                // System.out.println("STOPPED");
                return;
            }

            visit(current);

            switch (current.value) {
                case '.':
                    current = grid.get(current.r + dir[0]).get(current.c + dir[1]);
                    break;
                case '/':
                    if (dir[0] == 0 && dir[1] == 1) { // I was going right -> I should go up
                        dir[0] = -1;
                        dir[1] = 0;
                    } else if (dir[0] == 0 && dir[1] == -1) { // I was going left -> I should go down
                        dir[0] = 1;
                        dir[1] = 0;
                    } else if (dir[0] == 1 && dir[1] == 0) { // I was going down -> I should go left
                        dir[0] = 0;
                        dir[1] = -1;
                    } else if (dir[0] == -1 && dir[1] == 0) { // I was going up -> I should go right
                        dir[0] = 0;
                        dir[1] = 1;
                    }

                    current = grid.get(current.r + dir[0]).get(current.c + dir[1]);
                    break;
                case '\\':
                    if (dir[0] == 0 && dir[1] == 1) { // I was going right -> I should go down
                        dir[0] = 1;
                        dir[1] = 0;
                    } else if (dir[0] == 0 && dir[1] == -1) { // I was going left -> I should go up
                        dir[0] = -1;
                        dir[1] = 0;
                    } else if (dir[0] == 1 && dir[1] == 0) { // I was going down -> I should go right
                        dir[0] = 0;
                        dir[1] = 1;
                    } else if (dir[0] == -1 && dir[1] == 0) { // I was going up -> I should go left
                        dir[0] = 0;
                        dir[1] = -1;
                    }

                    current = grid.get(current.r + dir[0]).get(current.c + dir[1]);
                    break;
                case '|':
                    if (dir[0] == 0) { // I hit it on the flat side
                        castRay(grid, grid.get(current.r - 1).get(current.c), new int[] { -1, 0 });
                        castRay(grid, grid.get(current.r + 1).get(current.c), new int[] { 1, 0 });
                        return;
                    } else { // I hit it on the pointy side
                        current = grid.get(current.r + dir[0]).get(current.c + dir[1]);
                    }
                    break;
                case '-':
                    if (dir[1] == 0) { // I hit it on the pointy side
                        castRay(grid, grid.get(current.r).get(current.c - 1), new int[] { 0, -1 });
                        castRay(grid, grid.get(current.r).get(current.c + 1), new int[] { 0, 1 });
                        return;
                    } else { // I hit it on the flat side
                        current = grid.get(current.r + dir[0]).get(current.c + dir[1]);
                    }
                    break;
            }
        }

    }

    public static void visit(Cell c) {
        if (visitedCells.containsKey(c)) {
            visitedCells.replace(c, visitedCells.get(c) + 1);
        } else {
            visitedCells.put(c, 1);
        }
    }

    public static void printVisited(ArrayList<ArrayList<Cell>> grid) {
        for (ArrayList<Cell> row : grid) {
            for (Cell cell : row) {
                if (visitedCells.containsKey(cell))
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Cell>> grid = parseInput();
        ArrayList<Integer> counts = new ArrayList<>();
        print(grid);
        for (int i = 1; i < grid.size() - 1; i++) {
            castRay(grid, grid.get(i).get(1), new int[] { 0, 1 });
            counts.add(visitedCells.size());
            visitedCells.clear();
            cache.clear();
            castRay(grid, grid.get(i).get(grid.get(i).size() - 2), new int[] { 0, -1 });
            counts.add(visitedCells.size());
            visitedCells.clear();
            cache.clear();
        }
        
        for (int i = 1; i < grid.get(0).size() - 1; i++) {
            castRay(grid, grid.get(1).get(i), new int[] { 1, 0 });
            counts.add(visitedCells.size());
            visitedCells.clear();
            cache.clear();
            castRay(grid, grid.get(1).get(grid.size() - 2), new int[] { -1, 0 });
            counts.add(visitedCells.size());
            visitedCells.clear();
            cache.clear();
        }

        System.out.println(counts);
        System.out.println("Max: " + counts.stream().max((o1, o2) -> o1 - o2).get());
    }
}