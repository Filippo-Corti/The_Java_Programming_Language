import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    static class Game {
        int number;
        ArrayList<Extraction> extractions;

        @Override
        public String toString() {
            return "Game" + number + ": " + extractions.toString();
        }

        public boolean isValid(int i, int j, int k) {
            for (Extraction extraction : extractions) {
                if (!(extraction.reds <= i && extraction.greens <= j && extraction.blues <= k))
                    return false;
            }
            return true;
        }
    }

    static class Extraction {
        int reds;
        int greens;
        int blues;

        @Override
        public String toString() {
            return "R: " + reds + ", G: " + greens + ", B: " + blues + ";";
        }
    }

    public static ArrayList<Game> parseInput() {

        ArrayList<Game> in = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        int i = 1;

        while (s.hasNextLine()) {
            String strIn = s.nextLine().split(": ")[1]; // 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            String[] extractions = strIn.split("; "); // ["3 blue, 4 red", "1red, 2green", ...]

            Game game = new Game();
            game.number = i++;

            ArrayList<Extraction> es = new ArrayList<>();

            for (String extraction : extractions) {
                Matcher m = Pattern.compile("([0-9]+) (red|blue|green)").matcher(extraction);
                Extraction e = new Extraction();
                while (m.find()) {
                    String[] match = m.group().split(" ");
                    switch (match[1]) {
                        case "red":
                            e.reds = Integer.parseInt(match[0]);
                            break;
                        case "green":
                            e.greens = Integer.parseInt(match[0]);
                            break;
                        case "blue":
                            e.blues = Integer.parseInt(match[0]);
                            break;
                        default:
                            break;
                    }
                }
                es.add(e);
            }
            game.extractions = es;
            in.add(game);
        }

        return in;
    }

    public static void main(String[] args) {
        ArrayList<Game> input = parseInput();

        int count = 0;
        for (Game game : input) {
            if (game.isValid(12, 13, 14))
                count += game.number;
        }

        System.out.println(count);
    }

}
