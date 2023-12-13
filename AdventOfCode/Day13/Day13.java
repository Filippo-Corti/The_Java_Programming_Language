import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day13 {

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }

    public static String reverse(String s) {
        String reverse = "";
        for (int i = s.length() - 1; i > 0; i--) {
            reverse += s.charAt(i);
        }
        return reverse;
    }

    static class Pattern {
        ArrayList<String> rows;
        ArrayList<String> cols;

        public Pattern(ArrayList<String> rows, ArrayList<String> cols) {
            this.rows = new ArrayList<>(rows);
            this.cols = new ArrayList<>(cols);
        }

        @Override
        public String toString() {
            return "Pattern [rows=" + rows + ", cols=" + cols + "]";
        }

        public int lineOfReflection(int j) {
            String currentRow = rows.get(j);
            for (int i = 1; i < currentRow.length(); i++) {
                String before = currentRow.substring(0, i);
                String after = currentRow.substring(i+1);
                String toCheck = null;
                if (after.length() > before.length()) {
                    toCheck = reverse(after.substring(before.length())) + before + after;
                }
                System.out.println(toCheck);
                if(!isPalindrome(toCheck)) {
                    if(j == cols.size()) {
                        return i;
                    }
                    return lineOfReflection(j+1);
                }
            }
            return -1;
        }
        
    }

     public static ArrayList<Pattern> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Pattern> patterns = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<String> cols = new ArrayList<>();

        while (s.hasNextLine()) {

            String in = s.nextLine();

            if (in.equals("")) {
                patterns.add(new Pattern(rows, cols));
                rows.clear();
                cols.clear();
                continue;
            }

            rows.add(in);
            for (int i = 0; i < in.length(); i++) {
                if (cols.size() < i + 1) {
                    cols.add("");
                }
                cols.set(i, cols.get(i) + in.charAt(i));
            }

        }

        return patterns;
    }

    public static void main(String[] args) {
        ArrayList<Pattern> input = parseInput();
        input.get(0).lineOfReflection(0);
   }

    

}
