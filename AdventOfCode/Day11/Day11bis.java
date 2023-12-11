import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11bis {

    static class Map {
        ArrayList<Galaxy> galaxies;
        ArrayList<Integer> righeVuote;
        ArrayList<Integer> colonneVuote;

    }

    static class Galaxy {
        int x, y;

        public Galaxy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Galaxy {" + x + ", " + y + "}";
        }
    }

    public static Map parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<String> inputString = new ArrayList<>();

        while (s.hasNextLine()) {
            inputString.add(s.nextLine());
        }

        // Check for empty rows
        ArrayList<Integer> emptyRows = new ArrayList<>();
        for (int i = 0; i < inputString.size(); i++) {
            if (!inputString.get(i).contains("#")) {
                emptyRows.add(i);
            }
        }

        // Check for empty columns
        ArrayList<Boolean> fullCol = new ArrayList<Boolean>(Collections.nCopies(inputString.get(0).length(), false));
        for (String string : inputString) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '#')
                    fullCol.set(i, true);
            }
        }

        ArrayList<Integer> emptyColumns = new ArrayList<>();
        int i = 0;
        for (Boolean b : fullCol) {
            if (!b)
                emptyColumns.add(i);
            i++;
        }

        // Create ArrayList of Galaxies

        ArrayList<Galaxy> galaxies = new ArrayList<>();

        int x = 0;
        for (String string : inputString) {
            Matcher m = Pattern.compile("[#]").matcher(string);
            while (m.find()) {
                int y = m.start();
                galaxies.add(new Galaxy(x, y));
            }
            x++;
        }

        Map m = new Map();
        m.galaxies = galaxies;
        m.righeVuote = emptyRows;
        m.colonneVuote = emptyColumns;
        return m;
    }

    private static long calcDistance(Galaxy galaxy1, Galaxy galaxy2, ArrayList<Integer> emptyRows, ArrayList<Integer> emptyCols) {
        int xMin = (galaxy1.x < galaxy2.x) ? galaxy1.x : galaxy2.x;
        int xMax = (galaxy1.x < galaxy2.x) ? galaxy2.x : galaxy1.x;
        int yMin = (galaxy1.y < galaxy2.y) ? galaxy1.y : galaxy2.y;
        int yMax = (galaxy1.y < galaxy2.y) ? galaxy2.y : galaxy1.y;
        int deltax = xMax - xMin;
        int deltay = yMax - yMin;

        int deltaMin = (deltax < deltay) ? deltax : deltay;
        int deltaMax = (deltax > deltay) ? deltax : deltay;

        long res = deltaMin*2 + (deltaMax - deltaMin);
        for (int i = xMin; i < xMax; i++) {
            if (emptyRows.contains(i)) 
                res += 999999;
        }
        for (int i = yMin; i < yMax; i++) {
            if (emptyCols.contains(i)) 
                res += 999999;
        }
        return res;
    }

    public static void main(String[] args) {
        Map input = parseInput();
        long tot = 0;
        for (int i = 0; i < input.galaxies.size() - 1; i++) {
            for (int j = i + 1; j < input.galaxies.size(); j++) {
                tot += calcDistance(input.galaxies.get(i), input.galaxies.get(j), input.righeVuote, input.colonneVuote);
            }
        }

        System.out.println(tot);
    }

}
