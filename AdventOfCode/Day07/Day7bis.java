import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Day7bis {

    static class Hand implements Comparable<Hand> {

        static ArrayList<Character> labels = new ArrayList<>(
                Arrays.asList('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'));
        String cards;
        int bid;

        public Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
        }

        public int getBid() {
            return bid;
        }

        public int getScore() {
            if (cards.contains("J")) {

            }
            HashMap<Character, Integer> chars = mapCards();
            Collection<Integer> values = chars.values();
            if (cards.contains("J")) {
                switch (chars.size()) {
                    case 1:
                        return 7; // Five of a Kind
                    case 2:
                        return 7;
                    case 3:
                        if (values.contains(3)) {
                            return 6;
                        }
                        if (chars.get('J') == 2) 
                            return 6;
                        return 5;
                    case 4:
                        return 4;
                    case 5:
                        return 2;
                    default:
                        return -1;
                }
            }
            switch (chars.size()) {
                case 1:
                    return 7; // Five of a Kind
                case 2:
                    if (values.contains(4))
                        return 6; // Four of a Kind
                    return 5; // Full house
                case 3:
                    if (values.contains(3))
                        return 4; // Three of a Kind
                    return 3; // Two pair
                case 4:
                    return 2; // One pair
                case 5:
                    return 1; // High card
                default:
                    return -1;
            }
        }

        public HashMap<Character, Integer> mapCards() {
            HashMap<Character, Integer> chars = new HashMap<>();

            for (int i = 0; i < cards.length(); i++) {
                chars.put(cards.charAt(i), ((chars.get(cards.charAt(i)) != null) ? chars.get(cards.charAt(i)) : 0) + 1);
            }

            return chars;
        }

        @Override
        public int compareTo(Day7bis.Hand o) {
            int score1 = this.getScore();
            int score2 = o.getScore();
            if (score1 != score2) {
                return score1 - score2;
            }
            for (int i = 0; i < cards.length(); i++) {
                if (this.cards.charAt(i) != o.cards.charAt(i)) {
                    return labels.indexOf(this.cards.charAt(i)) - labels.indexOf(o.cards.charAt(i));
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return cards + " " + bid;
        }
    }

    public static ArrayList<Hand> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Hand> hands = new ArrayList<>();

        while (s.hasNext()) {
            hands.add(new Hand(s.next(), s.nextInt()));
        }
        return hands;
    }

    public static void main(String[] args) {

        ArrayList<Hand> hands = parseInput();
        hands.sort(null);
        int res = 0;
        for (int i = 1; i <= hands.size(); i++) {
            res += hands.get(i - 1).bid * i;
        }

        System.out.println(res);

    }

}