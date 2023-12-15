import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Patch;

public class Day12bis {

    static class ConditionRecord {
        String data;
        ArrayList<Integer> blocks;

        public ConditionRecord(String data, ArrayList<Integer> blocks) {
            this.data = data;
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return data + " | " + blocks;
        }

    }

    public static int calculateArrangementsPoint(ConditionRecord cr) {
        ConditionRecord newCr = new ConditionRecord(cr.data.substring(1), cr.blocks);
        return calculateArrangements(newCr);
    }

    public static int calculateArrangementsSpring(ConditionRecord cr) {
        int i;
        for (i = 0; i < cr.blocks.get(0); i++) {
            if (cr.data.length() < i+1) {
                return 0;
            }
            if (cr.data.charAt(i) == '.') {
                return 0; // Can't work
            }
        }

        if (cr.blocks.size() != 1) {
            if (cr.data.length() < cr.blocks.get(0)) {
                return 0;
            }
            if (cr.data.length() >= i) {
                if(cr.blocks.size() == 1) 
                    return 1;
            }
            if (cr.data.charAt(i) == '#') {
                return 0; // Can't work
            }
        } else {
            return 1;
        }

        ConditionRecord newCr = new ConditionRecord(cr.data.substring(i+1),
                new ArrayList<>(cr.blocks.subList(1, cr.blocks.size())));

        return calculateArrangements(newCr);
    }

    public static int calculateArrangements(ConditionRecord cr) {

        // Base Case
        if(cr.data.length() == 0) {
            if (cr.blocks.size() == 0) 
                return 1;
            else
                return 0;
        }

        // Recursive Case
        char currentChar = cr.data.charAt(0);

        if (currentChar == '.') {
            int x = calculateArrangementsPoint(cr);
              System.out.println(cr + " ritorna " + x);
            return x;
        }

        if (currentChar == '#') {
            int x = calculateArrangementsSpring(cr);
            System.out.println(cr + " ritorna " + x);
            return x;
        }

        if (currentChar == '?') {
            int x = calculateArrangementsSpring(cr);
            System.out.println("#" + cr.toString().substring(1) + " ritorna " + x);
            int y = calculateArrangementsPoint(cr);
            System.out.println("." + cr.toString().substring(1) + " ritorna " + y);
            return x+y;
        }

        return 0;

    }

    public static ArrayList<ConditionRecord> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ConditionRecord> input = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            ArrayList<Integer> blocks = new ArrayList<>();
            in[0] = in[0].repeat(5);
            in[1] = in[1].repeat(5);
            String[] inBlocks = in[1].split(",");
            for (String string : inBlocks) {
                blocks.add(Integer.parseInt(string));
            }

            ConditionRecord cr = new ConditionRecord(in[0], blocks);

            input.add(cr);
        }

        return input;
    }

    public static ArrayList<ConditionRecord> p1parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ConditionRecord> input = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            ArrayList<Integer> blocks = new ArrayList<>();
            String[] inBlocks = in[1].split(",");
            for (String string : inBlocks) {
                blocks.add(Integer.parseInt(string));
            }

            ConditionRecord cr = new ConditionRecord(in[0], blocks);

            input.add(cr);
        }

        return input;
    }

    public static void main(String[] args) {
        ArrayList<ConditionRecord> input = p1parseInput();
        //System.out.println(input.stream().mapToInt(cr -> calculateArrangements(cr)).sum());
        System.out.println(calculateArrangements(input.get(1)));
        /*for (ConditionRecord conditionRecord : input) {
            System.out.println(conditionRecord + " " + calculateArrangements(conditionRecord));
        }*/
    }

}