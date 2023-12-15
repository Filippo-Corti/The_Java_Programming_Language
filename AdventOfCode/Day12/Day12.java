import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day12 {

    static class ConditionRecord {
        String data;
        ArrayList<Integer> blocks;

        public int calculateArrangements() {
            HashSet<String> sequences = new HashSet<>();
            String untilFirstQuestionMark = untilNextQuestionMark(data);
            sequences.add(untilFirstQuestionMark);
            for (int i = 0; i < data.length();) {
                HashSet<String> newSequences = new HashSet<>();
                for (String sequence : sequences) {
                    sequence += untilNextQuestionMark(data.substring(sequence.length()));
                    if (sequence.length() == data.length()) {
                        if (isValid(sequence)) {
                          // System.out.println(sequence + " is valid");
                            newSequences.add(sequence);
                        } else {
                           // System.out.println(sequence + " is not valid");
                        }
                    } else {
                        String sequencePoint = sequence + ".";
                        String sequenceSpring = sequence + "#";
                        if (isValid(sequencePoint)) {
                          // System.out.println(sequencePoint + " is valid");
                            newSequences.add(sequencePoint);
                        } else {
                           // System.out.println(sequencePoint + " is not valid");
                        }
                        if (isValid(sequenceSpring)) {
                           // System.out.println(sequenceSpring + " is valid");
                            newSequences.add(sequenceSpring);
                        } else {
                          // System.out.println(sequenceSpring + " is not valid");
                        }
                    }
                    i = sequence.length() + 1;
                }
                sequences = newSequences;
            }
            return sequences.size();
        }

        private static String untilNextQuestionMark(String substring) {
            return (substring.contains("?") ? substring.substring(0, substring.indexOf("?")) : substring);
        }

        public boolean isValid(String s) {
            Matcher m = Pattern.compile("[#]+").matcher(s);
            int i = 0;
            while (m.find()) {
                String found = m.group();
                i++;
                if (i > blocks.size()) {
                    break;
                }
                if (found.length() != blocks.get(i - 1) && s.length() == data.length()) {
                    return false;
                }
            }
            if (s.length() == data.length() && i != blocks.size()) {
               // System.out.println(s + " false " + i);
                return false;
            }
           // System.out.println(s + " true " + i);
            return true;
        }


        @Override
        public String toString() {
            return data + " | " + blocks;
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
      // System.out.println(input.stream().mapToInt(ConditionRecord::calculateArrangements).sum());
        
        for (ConditionRecord conditionRecord : input) {
            System.out.println(conditionRecord + " " + conditionRecord.calculateArrangements());
        }
    }

}