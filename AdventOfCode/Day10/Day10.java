import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {

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
        Cell start = game.map.get(game.rS).get(game.rS);

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

        System.out.println(game.visited);
        System.out.println(game.visited.size() / 2);
    }

    private static Cell findNextDirection(Game game, Cell curr, Cell prev) {
        switch (curr.value) {
            case '|':
                if (prev.row > curr.row) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row + 1).get(curr.col); //Going DOWN
            case '-':
                if (prev.col > curr.col) // Going LEFT
                    return game.map.get(curr.row).get(curr.col - 1);
                return game.map.get(curr.row).get(curr.col + 1); //Going RIGHT
            case 'L':
                if (prev.col != curr.col) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col + 1); //Going RIGHT
            case 'J':
                if (prev.col != curr.col) // Going UP
                    return game.map.get(curr.row - 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col - 1); //Going LEFT
            case '7':
                if (prev.col != curr.col) // Going DOWN
                    return game.map.get(curr.row + 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col - 1); //Going LEFT
            case 'F':
                if (prev.col != curr.col) // Going DOWN
                    return game.map.get(curr.row + 1).get(curr.col);
                return game.map.get(curr.row).get(curr.col + 1); //Going RIGHT
            default:
                System.out.println("Abbiamo un problema");
                break;
        }
        return null;
    }

    private static Cell findFirstDirection(Game game) {
        if (game.map.get(game.rS - 1).get(game.cS).value == '|' || game.map.get(game.rS - 1).get(game.cS).value == '7'
                || game.map.get(game.rS - 1).get(game.cS).value == 'F')
            return game.map.get(game.rS - 1).get(game.cS);
        if (game.map.get(game.rS + 1).get(game.cS).value == '|' || game.map.get(game.rS + 1).get(game.cS).value == 'J'
                || game.map.get(game.rS + 1).get(game.cS).value == 'L')
            return game.map.get(game.rS + 1).get(game.cS);
        if (game.map.get(game.rS).get(game.cS - 1).value == '-' || game.map.get(game.rS).get(game.cS - 1).value == 'F'
                || game.map.get(game.rS).get(game.cS - 1).value == 'L')
            return game.map.get(game.rS).get(game.cS - 1);
        if (game.map.get(game.rS).get(game.cS + 1).value == '-' || game.map.get(game.rS).get(game.cS + 1).value == '7'
                || game.map.get(game.rS).get(game.cS + 1).value == 'J')
            return game.map.get(game.rS).get(game.cS + 1);
        return null;
    }

    public static void main(String[] args) {
        Game game = parseInput();
        playGame(game);
    }

}