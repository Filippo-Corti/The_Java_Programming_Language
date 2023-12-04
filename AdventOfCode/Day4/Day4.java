import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Day4 {

    static class Card {

        
        ArrayList<Integer> winners;
        ArrayList<Integer> found;

        public Card(ArrayList<Integer> w, ArrayList<Integer> f) {
            this.winners = w;
            this.found = f;
        }

        public int getScore() {
            int c = 0;

            for (int num : found) {
                for (int winner : winners) {
                    if (num == winner) {
                        c++;
                    }
                }
            }
            return (c == 0) ? 0 : (int) (Math.pow(2., c - 1));
        }
    }

    public static ArrayList<Card> parseInput() {

        ArrayList<Card> input = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            String in = s.nextLine().split(":")[1];
            String[] inSplit = in.split("\\|");

            String[] winnings = inSplit[0].split(" ");

            ArrayList<Integer> winningsInt = new ArrayList<>();

            for (String string : winnings) {
                if (!string.equals(""))
                    winningsInt.add(Integer.parseInt(string));
            }

            String[] found = inSplit[1].split(" ");

            ArrayList<Integer> foundInt = new ArrayList<>();

            for (String string : found) {
                if (!string.equals(""))
                    foundInt.add(Integer.parseInt(string));
            }

            input.add(new Card(winningsInt, foundInt));

        }

        return input;
    }

    public static void main(String[] args) {
        ArrayList<Card> input = parseInput();

        System.out.println(input.stream().mapToInt(Card::getScore).sum());
    }

}