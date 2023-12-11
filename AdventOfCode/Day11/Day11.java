import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {

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

    public static ArrayList<Galaxy> parseInput() {

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
        for (int i = 0; i < inputString.size(); i++) {
            if (!inputString.get(i).contains("#")) {
                inputString.add(i + 1, inputString.get(i));
                i++;
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

        int spostate = 0;
        for (int j = 0; j < fullCol.size(); j++) {
            if (!fullCol.get(j)) {
                // Col is empty
                for (int i = 0; i < inputString.size(); i++) {
                    inputString.set(i, inputString.get(i).substring(0, j+spostate+1) + "." + inputString.get(i).substring(j+spostate+1));
                }
                spostate++;
            }
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

        return galaxies;
    }

    private static int calcDistance(Galaxy galaxy1, Galaxy galaxy2) {
        int deltax = Math.abs(galaxy2.x - galaxy1.x);
        int deltay = Math.abs(galaxy2.y - galaxy1.y);

        int deltaMin = (deltax < deltay) ? deltax : deltay;
        int deltaMax = (deltax > deltay) ? deltax : deltay;

        return deltaMin*2 + (deltaMax - deltaMin); 
    }

    public static void main(String[] args) {
        ArrayList<Galaxy> input = parseInput();
        int tot = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = i + 1; j < input.size(); j++) {
                tot += calcDistance(input.get(i), input.get(j));
            }
        }

        System.out.println(tot);
    }

}
