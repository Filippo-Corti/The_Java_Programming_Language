import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day1bis {

    public static ArrayList<String> parseInput() {

        ArrayList<String> in = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            in.add(s.nextLine());
        }

        return in;
    }

    public static int firstDigit(String s) {
        ArrayList<String> matches = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine"));

        int indexMin = s.length() + 1;
        String min = "";

        for (String match : matches) {
            int indexCurr = s.indexOf(match);
            if (indexCurr >= 0 && indexCurr < indexMin) {
                indexMin = indexCurr;
                min = match;
            }
        }

        return matches.indexOf(min) % 9 + 1;

    }

    public static int lastDigit(String s) {

        StringBuilder sb = new StringBuilder(s);
        s = sb.reverse().toString();

        ArrayList<String> matches = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "eno",
                "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin"));

        int indexMin = s.length() + 1;
        String min = "";

        for (String match : matches) {
            int indexCurr = s.indexOf(match);
            if (indexCurr >= 0 && indexCurr < indexMin) {
                indexMin = indexCurr;
                min = match;
            }
        }

        return matches.indexOf(min) % 9 + 1;
    }

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();

        System.out.println(input.stream().mapToInt(s -> firstDigit(s) * 10 + lastDigit(s)).reduce(0, (s, el) -> s + el));

    }

}
