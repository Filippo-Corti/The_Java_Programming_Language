import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Day4bis {

    static class Card {

        int copies = 1;
        ArrayList<Integer> winners;
        ArrayList<Integer> found;

        public Card(ArrayList<Integer> w, ArrayList<Integer> f) {
            this.winners = w;
            this.found = f;
        }

        public int getCopies() {
            return copies;
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
            return c;
        }
    }

    public static void updateCopies(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            int score = cards.get(i).getScore();
            for (int k = 0; k < cards.get(i).copies; k++) {
                for (int j = 1; j <= score && i + j < cards.size(); j++) {
                    cards.get(i + j).copies++;
                }
            }
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

        updateCopies(input);

        System.out.println(input.stream().mapToInt(Card::getCopies).sum());
    }

}