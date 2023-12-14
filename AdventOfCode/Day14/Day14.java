import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14 {

    public static ArrayList<ArrayList<Character>> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Character>> platform = new ArrayList<>();

        while (s.hasNextLine()) {
            String row = s.nextLine();

            for (int i = 0; i < row.length(); i++) {
                if (platform.size() < i + 1) {
                    platform.add(new ArrayList<>());
                }
                platform.get(i).add(row.charAt(i));
            }

        }
        return platform;
    }

    public static void print(ArrayList<ArrayList<Character>> platform) {
        for (ArrayList<Character> row : platform) {
            for (Character ch : row) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    // Collapse North
    public static void collapseLeft(ArrayList<ArrayList<Character>> platform) {
        for (int i = 0; i < platform.size(); i++) {
            ArrayList<Character> row = platform.get(i);
            int collapseTo = 0;
            for (int j = 0; j < row.size(); j++) {
                char c = row.get(j);
                if (c == '#')
                    collapseTo = j + 1;
                else if (c == 'O') {
                    row.remove(j);
                    row.add(j, '.');
                    row.remove(collapseTo);
                    row.add(collapseTo, 'O');
                    collapseTo++;
                }
            }
        }
    }

    public static int countLoad(ArrayList<ArrayList<Character>> platform) {
        int load = 0;
        for (ArrayList<Character> row : platform) {
            int i = row.size();
            for (Character ch : row) {
                if (ch == 'O')
                    load += i;
                i--;
            }
        }
        return load;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> input = parseInput();
        print(input);
        collapseLeft(input);
        print(input);
        System.out.println(countLoad(input));
    }

}
