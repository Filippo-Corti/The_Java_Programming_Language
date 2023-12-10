import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10bis {

    static class Game {
        ArrayList<ArrayList<Cell>> map;
        ArrayList<Cell> visited = new ArrayList<>();
        int rS, cS;
    }

    static class Cell {
        int row, col;
        char value;

        public Cell(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Cell[" + row + ", " + col + "] -> " + value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj == null)
                return false;
            if (obj.getClass() != this.getClass())
                return false;
            Cell c = (Cell) obj;
            if (c.row != this.row)
                return false;
            if (c.col != this.col)
                return false;
            return true;
        }

    }

    public static Game parseInput() {

        Game game = new Game();

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Cell>> matrix = new ArrayList<>();

        int j = 0;
        while (s.hasNextLine()) {
            String in = s.nextLine();

            ArrayList<Cell> row = new ArrayList<>();

            for (int i = 0; i < in.length(); i++) {
                if (in.charAt(i) == 'S') {
                    game.rS = j;
                    game.cS = i;
                }
                row.add(new Cell(j, i, in.charAt(i)));

            }

            matrix.add(row);
            j++;
        }

        game.map = matrix;
        return game;
    }

    private static void playGame(Game game) {
        Cell start = game.map.get(game.rS).get(game.cS);

        game.visited.add(start);
        Cell prev = start;
        Cell curr = findFirstDirection(game);
        game.visited.add(curr);
        while (curr.value != 'S') {
            Cell next = findNextDirection(game, curr, prev);
            game.visited.add(next);
            prev = curr;
            curr = next;
        }

        printVisited(game);
        // System.out.println(game.visited.size() / 2);
    }

    private static Cell findNextDirection(Game game, Cell curr, Cell prev) {
        switch (curr.value) {
            case '|':
                if (prev.row > curr.row) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row + 1).get(curr.col); // Going DOWN
            case '-':
                if (prev.col > curr.col) // Going LEFT
                    return game.map.get(curr.row).get(curr.col - 1);
                return game.map.get(curr.row).get(curr.col + 1); // Going RIGHT
            case 'L':
                if (prev.col != curr.col) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col + 1); // Going RIGHT
            case 'J':
                if (prev.col != curr.col) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col - 1); // Going LEFT
            case '7':
                if (prev.col != curr.col) // Going DOWN
                    return game.map.get(curr.row + 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col - 1); // Going LEFT
            case 'F':
                if (prev.col != curr.col) // Going DOWN
                    return game.map.get(curr.row + 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col + 1); // Going RIGHT
            default:
                System.out.println("Abbiamo un problema");
                break;
        }
        return null;
    }

    private static Cell findFirstDirection(Game game) {
        try {
            if (game.map.get(game.rS - 1).get(game.cS).value == '|'
                    || game.map.get(game.rS - 1).get(game.cS).value == '7'
                    || game.map.get(game.rS - 1).get(game.cS).value == 'F')
                return game.map.get(game.rS - 1).get(game.cS);
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (game.map.get(game.rS + 1).get(game.cS).value == '|'
                    || game.map.get(game.rS + 1).get(game.cS).value == 'J'
                    || game.map.get(game.rS + 1).get(game.cS).value == 'L')
                return game.map.get(game.rS + 1).get(game.cS);
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (game.map.get(game.rS).get(game.cS - 1).value == '-'
                    || game.map.get(game.rS).get(game.cS - 1).value == 'F'
                    || game.map.get(game.rS).get(game.cS - 1).value == 'L')
                return game.map.get(game.rS).get(game.cS - 1);
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (game.map.get(game.rS).get(game.cS + 1).value == '-'
                    || game.map.get(game.rS).get(game.cS + 1).value == '7'
                    || game.map.get(game.rS).get(game.cS + 1).value == 'J')
                return game.map.get(game.rS).get(game.cS + 1);
        } catch (IndexOutOfBoundsException e) {
        }
        return null;
    }

    private static void printVisited(Game game) {
        int countIn = 0;
        for (ArrayList<Cell> row : game.map) {
            String line = "";
            // Build line
            for (Cell cell : row) {
                // Purtroppo devo cambiare a mano S se no impazzisco
                if (game.visited.contains(cell)) {
                    if (cell.value == 'S')
                        line += '-';
                    else
                        line += cell.value;
                } else {
                    line += ".";
                }
            }
            // Deal with false Changes of Inside-Outside
            Pattern falseChangeOfContext1 = Pattern.compile("F[-]*7");
            Pattern falseChangeOfContext2 = Pattern.compile("L[-]*J");
            Matcher m1 = falseChangeOfContext1.matcher(line);
            Matcher m2 = falseChangeOfContext2.matcher(line);
            while (m1.find()) {
                String found = m1.group();
                line = line.replace(found, "X".repeat(found.length()));
            }
            while (m2.find()) {
                String found = m2.group();
                line = line.replace(found, "X".repeat(found.length()));
            }
            // Deal with real Changes of Inside-Outside
            Pattern realChangeOfContext1 = Pattern.compile("F[-]*J");
            Pattern realChangeOfContext2 = Pattern.compile("L[-]*7");
            Matcher m3 = realChangeOfContext1.matcher(line);
            Matcher m4 = realChangeOfContext2.matcher(line);
            while (m3.find()) {
                String found = m3.group();
                line = line.replace(found, "S" + "Y".repeat(found.length() - 2) + "E");
            }
            while (m4.find()) {
                String found = m4.group();
                line = line.replace(found, "S" + "Y".repeat(found.length() - 2) + "E");
            }
            // Find Inside & Outside
            boolean outside = true;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'S') {
                    while (c != 'E' && i < line.length() - 1) {
                        i++;
                        c = line.charAt(i);
                        System.out.print(c);
                    }
                    outside = !outside;
                }
                if (c == '|')
                    outside = !outside;
                if (c == '.') {
                    if (outside)
                        System.out.print("O");
                    else {
                        System.out.print("I");
                        countIn++;
                    }
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();

        }
        System.out.println("CountIn: " + countIn);
    }

    public static void main(String[] args) {
        Game game = parseInput();
        playGame(game);
    }

}