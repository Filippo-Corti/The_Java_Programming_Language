import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18bis {

    static class Direction {
        int dx;
        int dy;
        String color;

        public Direction(int dx, int dy, String color) {
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + dx + ", " + dy + ")";
        }

    }

    public static ArrayList<Direction> parseInput() {

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
            int dValue = Integer.parseInt(in.substring(2, 7), 16);
            int dx = 0, dy = 0;
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

    public static ArrayList<ArrayList<Character>> drawMatrix(ArrayList<Direction> directions) {

        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

        // Init Matrix
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
        int currX = 0, currY = 0;

        for (Direction direction : directions) {
            currX += direction.dx;
            currY += direction.dy;

            if (currX > maxX)
                maxX = currX;
            if (currX < minX)
                minX = currX;
            if (currY > maxY)
                maxY = currY;
            if (currY < minY)
                minY = currY;

        }

        int rows = (maxY - minY + 1);
        int cols = (maxX - minX + 1);

        for (int i = 0; i < rows; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add('.');
            }
            matrix.add(row);
        }

        // Fill Matrix
        currX = Math.abs(minX);
        currY = Math.abs(minY);

        for (Direction direction : directions) {
            if (direction.dx > 0) {
                for (int i = 0; i < direction.dx; i++) {
                    currX++;
                    matrix.get(currY).set(currX, '#');
                }
            } else {
                for (int i = 0; i < -direction.dx; i++) {
                    currX--;
                    matrix.get(currY).set(currX, '#');
                }
            }

            if (direction.dy > 0) {
                for (int i = 0; i < direction.dy; i++) {
                    currY++;
                    matrix.get(currY).set(currX, '#');
                }
            } else {
                for (int i = 0; i < -direction.dy; i++) {
                    currY--;
                    matrix.get(currY).set(currX, '#');
                }
            }
        }

        return matrix;
    }

    public static void floodFill(ArrayList<ArrayList<Character>> matrix, int r, int c) {

        if (r < 0 || r >= matrix.size() || c < 0 || c >= matrix.get(0).size() || matrix.get(r).get(c) == '#')
            return;
        
        matrix.get(r).set(c, '#');

        floodFill(matrix, r+1, c);
        floodFill(matrix, r-1, c);
        floodFill(matrix, r, c+1);
        floodFill(matrix, r, c-1);

    }

    public static int print(ArrayList<ArrayList<Character>> matrix) {
        int c = 0;
        for (ArrayList<Character> row : matrix) {
            for (Character cell : row) {
                if (cell == '#')
                    c++;
               // System.out.print(cell);
            }
          //  System.out.println();
        }
        return c;
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("err.txt");
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setErr(ps);

        ArrayList<Direction> directions = parseInput();
        ArrayList<ArrayList<Character>> matrix = drawMatrix(directions);
        System.out.println("Matrix OK");
        floodFill(matrix, 50, 180);
        System.out.println("Fill OK");
       System.out.println("Count: " + print(matrix));
    }

}
