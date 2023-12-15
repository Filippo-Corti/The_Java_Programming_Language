import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day15bis {

    public static ArrayList<String> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<String> steps = new ArrayList<>(Arrays.asList(s.nextLine().split(",")));

        return steps;
    }

    public static int hash(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            value += s.charAt(i);
            value *= 17;
            value %= 256;a
        }
        return value;
    }


    public static void main(String[] args) {
        System.out.println(parseInput().stream().mapToInt(Day15::hash).sum());

    }


}
