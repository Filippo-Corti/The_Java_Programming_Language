import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day14bis {

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

            ArrayList<Character> rowCharacters = new ArrayList<>();
            for (int i = 0; i < row.length(); i++) {
                rowCharacters.add(row.charAt(i));
            }
            platform.add(rowCharacters);

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

    public static void collapseWest(ArrayList<ArrayList<Character>> platform) {
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

    public static void collapseEast(ArrayList<ArrayList<Character>> platform) {
        for (int i = 0; i < platform.size(); i++) {
            ArrayList<Character> row = platform.get(i);
            int collapseTo = row.size() - 1;
            for (int j = collapseTo; j >= 0; j--) {
                char c = row.get(j);
                if (c == '#')
                    collapseTo = j - 1;
                else if (c == 'O') {
                    row.remove(j);
                    row.add(j, '.');
                    row.remove(collapseTo);
                    row.add(collapseTo, 'O');
                    collapseTo--;
                }
            }
        }
    }

    public static void collapseNorth(ArrayList<ArrayList<Character>> platform) {
        for (int i = 0; i < platform.get(0).size(); i++) {
            int collapseTo = 0;
            for (int j = 0; j < platform.size(); j++) {
                ArrayList<Character> col = platform.get(j);
                char c = col.get(i);
                if (c == '#')
                    collapseTo = j + 1;
                else if (c == 'O') {
                    col.remove(i);
                    col.add(i, '.');
                    platform.get(collapseTo).remove(i);
                    platform.get(collapseTo).add(i, 'O');
                    collapseTo++;
                }
            }
        }
    }

    public static void collapseSouth(ArrayList<ArrayList<Character>> platform) {
        for (int i = 0; i < platform.get(0).size(); i++) {
            int collapseTo = platform.size() - 1;
            for (int j = collapseTo; j >= 0; j--) {
                ArrayList<Character> col = platform.get(j);
                char c = col.get(i);
                if (c == '#')
                    collapseTo = j - 1;
                else if (c == 'O') {
                    col.remove(i);
                    col.add(i, '.');
                    platform.get(collapseTo).remove(i);
                    platform.get(collapseTo).add(i, 'O');
                    collapseTo--;
                }
            }
        }
    }

    public static void spin(ArrayList<ArrayList<Character>> platform) {
        collapseNorth(platform);
        collapseWest(platform);
        collapseSouth(platform);
        collapseEast(platform);
    }

    public static int countLoad(ArrayList<ArrayList<Character>> platform) {
        int load = 0;
        int i = platform.size();
        for (ArrayList<Character> row : platform) {
            for (Character ch : row) {
                if (ch == 'O')
                    load += i;
            }
            i--;
        }
        return load;
    }

    public static String stringPlatform(ArrayList<ArrayList<Character>> platform) {
        String str = "";
        for (ArrayList<Character> arrayList : platform) {
            for (Character character : arrayList) {
                str += character;
            }
            str += "!";
        }
        return str;
    }

    public static ArrayList<ArrayList<Character>> unstringPlatform(String platform) {
        String[] rows = platform.split("!");
        ArrayList<ArrayList<Character>> newPlatform = new ArrayList<>();
        for (String row : rows) {
            ArrayList<Character> newRow = new ArrayList<>();
            for (int i = 0; i < row.length(); i++) {
                newRow.add(row.charAt(i));
            }
            newPlatform.add(newRow);
        }
        return newPlatform;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> input = parseInput();
        HashMap<String, String> cache = new HashMap<>();
       // print(input);
        // Ciclo 154 -> ... -> 179 (26 elementi)
        // System.out.println((1E9-154)%26+154); = 168
        for (int i = 1; i <= 1000; i++) {
            String stringified = stringPlatform(input);
            if (!cache.containsKey(stringified)) {
                spin(input);
                cache.put(stringified, i + " " + stringPlatform(input));
               //System.out.println("Calcolato" + i);
            } else {
                input = unstringPlatform(cache.get(stringified).split(" ")[1]);
                if (cache.get(stringified).split(" ")[0].equals("168")) {
                    System.out.println(countLoad(input));
                    return;
                }
                //System.out.println("Cachato" + i + "  " + cache.get(stringified).split(" ")[0]);
            }
        }
    }

}
