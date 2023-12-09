
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {

    public static ArrayList<ArrayList<Long>> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Long>> input = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            ArrayList<Long> inLine = new ArrayList<>();

            for (String str : in) {
                inLine.add(Long.parseLong(str));
            }

            input.add(inLine);
        }

        return input;
    }

    public static ArrayList<Long> calcDifferences(ArrayList<Long> l) {
        ArrayList<Long> differences = new ArrayList<>();
        for (int i = 1; i < l.size(); i++) {
            differences.add(l.get(i) - l.get(i - 1));
        }
        return differences;
    }

    public static long findNextNumber(ArrayList<Long> l) {
        if (l.stream().allMatch(entry -> entry == 0)) {
            return 0;
        }
        long ret = l.get(l.size() - 1) + findNextNumber(calcDifferences(l));
        return ret;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> input = parseInput();

        System.out.println(input.stream().mapToLong((list) -> findNextNumber(list)).sum());
    }

}