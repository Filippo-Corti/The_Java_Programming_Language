import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Patch;

public class Day12 {

    static class ConditionRecord {
        String data;
        ArrayList<Integer> blocks;

        public int calculateArrangements() {
            HashSet<String> valid = new HashSet<>();
            valid.add(data.substring(0, data.indexOf("?")));
            for (int i = 0; i < data.length(); i++) {
                HashSet<String> newValid = new HashSet<>();
                int j = i;
                for (String string : valid) { 
                    string += untilNextQuestionMark(data.substring(j));
                    i = string.length();
                    System.out.println("now checking" + string);
                    if (isValid(string + ".")) {
                        System.out.println(string + ". is valid");
                        newValid.add(string + ".");
                    }
                    if (isValid(string + "#"))
                        newValid.add(string + "#");
                }
                //System.out.println(newValid);
                valid = newValid;
            }
            //System.out.println(valid);
            return valid.size();
        }

        private static String untilNextQuestionMark(String substring) {
            for (int i = 0; i < substring.length(); i++) {
                if(substring.charAt(i) == '?')
                    return substring.substring(0, i);
            }
            return substring;
        }

        public boolean isValid(String s) {
            Matcher m = Pattern.compile("[#]+").matcher(s);
            int i = 1;
            try {
                while (m.find()) {
                    String found = m.group();
                    if (m.groupCount() == i) {
                        if (found.length() > blocks.get(i - 1)) {
                            return false;
                        }
                    } else {
                        if (found.length() != blocks.get(i - 1)) {
                            return false;
                        }
                    }
                    i++;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return data;
        }

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
            String[] inBlocks = in[1].split(",");
            for (String string : inBlocks) {
                blocks.add(Integer.parseInt(string));
            }

            ConditionRecord cr = new ConditionRecord();
            cr.data = in[0];
            cr.blocks = blocks;

            input.add(cr);
        }

        return input;
    }

    public static void main(String[] args) {
        ArrayList<ConditionRecord> input = parseInput();
        input.forEach((i) -> System.out.println(i.calculateArrangements()));
    }

}