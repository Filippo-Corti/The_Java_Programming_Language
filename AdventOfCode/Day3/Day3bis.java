
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Day3bis {

    public static ArrayList<String> parseInput() {

        ArrayList<String> in = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            in.add(s.nextLine());
        }
        return in;
    }

    public static int solvePart2(ArrayList<String> input) {
        int sum = 0;
        for (int i = 1; i < input.size() - 1; i++) {
            String curr = input.get(i);
            Matcher m = Pattern.compile("[*]").matcher(curr);
            while (m.find()) {
                // Find "*"
                String s = m.group();
                int index = m.start();
                ArrayList<Integer> found = new ArrayList<>();
                // Check right
                Matcher m2 = Pattern.compile("[0-9]+").matcher(curr.substring(index + 1));
                if (m2.find()) {
                    String numRight = m2.group();
                    if (m2.start() == 0) {
                        // Adiacente a "*"
                        found.add(Integer.parseInt(numRight));
                    }
                }
                // Check left
                Matcher m3 = Pattern.compile("[0-9]+").matcher(curr.substring(0, index));
                String numLeft = null;
                int end = 0;
                while (m3.find()) {
                    numLeft = m3.group();
                    end = m3.end();
                }
                if (end == index) {
                    // Adiacente a "*"
                    found.add(Integer.parseInt(numLeft));
                }
                // Check Above
                String aboveString = input.get(i - 1).substring(index - 3, index + 4);
                Matcher m4 = Pattern.compile("[0-9]+").matcher(aboveString);
                while (m4.find()) {
                    String numFound = m4.group();
                   // System.out.println("Number: " + numFound + "; Start: " + m4.start() + " - End: " + m4.end());
                    if (m4.end() >= 3 && m4.end() <= 5 || m4.start() >= 2 && m4.start() <= 4) {
                        found.add(Integer.parseInt(numFound));
                    }
                }
                // Check Below
                String belowString = input.get(i + 1).substring(index - 3, index + 4);
                Matcher m5 = Pattern.compile("[0-9]+").matcher(belowString);
                while (m5.find()) {
                    String numFound = m5.group();
                    if (m5.end() >= 3 && m5.end() <= 5 || m5.start() >= 2 && m5.start() <= 4) {
                        found.add(Integer.parseInt(numFound));
                    }
                }
                if (found.size() == 2) {
                    sum += found.get(0) * found.get(1);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();

        System.out.println(solvePart2(input));
    }

}
