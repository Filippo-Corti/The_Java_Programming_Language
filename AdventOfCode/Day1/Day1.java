import java.util.ArrayList;
import java.util.Scanner;
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

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();

       /* System.out.println(input.stream().mapToInt(
            value -> {
                Integer.parseInt(value.charAt(Pattern.compile("[0-9]").matcher(value).start()) + "") * 10 +  
                Integer.parseInt(value.charAt(Pattern.compile("(\\d)(?!.*\\d)").matcher(value).start()) + "")
                ).reduce(0, (s, el) -> s + el));
*/

        String x = "fawof1ji28dw";

        java.util.regex.Matcher m = Pattern.compile("(\\d)(?!.*\\d)").matcher(x);
        m.find();
        System.out.println(m.group());
    }

}
