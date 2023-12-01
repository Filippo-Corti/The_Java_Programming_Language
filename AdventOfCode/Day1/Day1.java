import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    public static ArrayList<String> parseInput() {

        ArrayList<String> in = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            in.add(s.nextLine());
        }

        return in;
    }

    public static int firstDigit(String s) {
        Matcher m = Pattern.compile("[0-9]").matcher(s);
        m.find();
        return Integer.parseInt(m.group());

        
    }

    public static int lastDigit(String s) {
        Matcher m = Pattern.compile("(\\d)(?!.*\\d)").matcher(s);
        m.find();
        return Integer.parseInt(m.group());
    }

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();

        System.out.println(input.stream().mapToInt(s -> firstDigit(s) * 10 + lastDigit(s)).reduce(0, (s, el) -> s + el));
    }

}
