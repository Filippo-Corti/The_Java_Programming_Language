
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Day3 {

    public static ArrayList<String> parseInput() {

        ArrayList<String> in = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            in.add(s.nextLine());
        }
        return in;
    }

    public static int solvePart1(ArrayList<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String curr = input.get(i);
            Matcher m = Pattern.compile("[0-9]+").matcher(curr);
            while (m.find()) {
                // Find Number
                String s = m.group();
                int index = m.start();
                // System.out.println("Stringa: " + s + ", Index: " + index + ", Length: " +
                // s.length());
                String above = null, below = null;
                if (i - 1 >= 0)
                    above = input.get(i - 1);
                if (i + 1 < input.size())
                    below = input.get(i + 1);
                if (isPartNumber(above, curr, below, index, s)) {
                    sum += Integer.parseInt(s);
                    System.out.println(s + ": Valido!");
                } else {
                    System.out.println(s + ": Non Valido!");
                }
            }
        }
        return sum;
    }

    private static boolean isPartNumber(String above, String curr, String below, int index, String number) {
        // Try LEFT and RIGHT
        if (((index - 1 >= 0) && curr.charAt(index - 1) != '.')
                || ((index + number.length() < curr.length()) && curr.charAt(index + number.length()) != '.'))
            return true;
        // Try Line Above
        if (above != null) {
            String substring = above.substring((index - 1 >= 0) ? index - 1 : 0,
                    (index + number.length() + 1 < above.length()) ? index + number.length() + 1 : above.length());
            if (!(substring).equals(".".repeat(substring.length())))
                return true;
        }
        // Try Line Below
        if (below != null) {
            String substring = below.substring((index - 1 >= 0) ? index - 1 : 0,
                    (index + number.length() + 1 < below.length()) ? index + number.length() + 1 : below.length());
            if (!(substring).equals(".".repeat(substring.length())))
                return true;
        }

        return false;

    }

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();

        System.out.println(solvePart1(input));
    }

}
