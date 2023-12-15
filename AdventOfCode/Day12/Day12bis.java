import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Day12bis {

    static HashMap<ConditionRecord, Long> cache = new HashMap<>();

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

        @Override
        public int hashCode() {
            return Objects.hash(data, blocks);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ConditionRecord other = (ConditionRecord) obj;
            if (data == null) {
                if (other.data != null)
                    return false;
            } else if (!data.equals(other.data))
                return false;
            if (blocks == null) {
                if (other.blocks != null)
                    return false;
            } else {
                if (blocks.size() != other.blocks.size())
                    return false;
                for (int i = 0; i < blocks.size(); i++) {
                    if (blocks.get(i) != other.blocks.get(i))
                        return false;
                }
            }
            return true;
        }

    }

    public static long calculateArrangementsPoint(ConditionRecord cr) {
        ConditionRecord newCr = new ConditionRecord(cr.data.substring(1), cr.blocks);
        return calculateArrangements(newCr);
    }

    public static long calculateArrangementsSpring(ConditionRecord cr) {
        int i;

        for (i = 0; i < cr.blocks.get(0); i++) {
            if (i >= cr.data.length())
                return 0;
            if (cr.data.charAt(i) == '.') {
                return 0; // Can't work
            }
        }

        if (i >= cr.data.length()) {
            if (cr.blocks.size() == 1)
                return 1;
            return 0;
        }

        if (cr.data.charAt(i) == '#') {
            return 0; // Can't work
        }

        ConditionRecord newCr = new ConditionRecord(cr.data.substring(i + 1),
                new ArrayList<>(cr.blocks.subList(1, cr.blocks.size())));

        return calculateArrangements(newCr);
    }

    public static long calculateArrangements(ConditionRecord cr) {

        if (cache.containsKey(cr))
            return cache.get(cr);

        // System.out.println("calculating arrangements for " + cr);

        // Base Case
        if (cr.data.length() == 0) {
            if (cr.blocks.size() == 0)
                return 1;
            return 0;
        }

        if (cr.blocks.size() == 0) {
            if (!(cr.data.contains("#")))
                return 1;
            return 0;
        }

        // Recursive Case
        char currentChar = cr.data.charAt(0);

        if (currentChar == '.') {
            long x = calculateArrangementsPoint(cr);
            // System.out.println(cr + " ritorna " + x);
            cache.put(cr, x);
            return x;
        }

        if (currentChar == '#') {
            long x = calculateArrangementsSpring(cr);
            // System.out.println(cr + " ritorna " + x);
            cache.put(cr, x);
            return x;
        }

        if (currentChar == '?') {
            long x = calculateArrangementsSpring(cr);
            // System.out.println("#" + cr.toString().substring(1) + " ritorna " + x);
            long y = calculateArrangementsPoint(cr);
            // System.out.println("." + cr.toString().substring(1) + " ritorna " + y);
            cache.put(cr, x + y);
            return x + y;
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
            in[0] = (in[0] + "?").repeat(5);
            in[1] = (in[1] + ",").repeat(5);
            String[] inBlocks = in[1].split(",");
            for (String string : inBlocks) {
                blocks.add(Integer.parseInt(string));
            }

            ConditionRecord cr = new ConditionRecord(in[0].substring(0, in[0].length() - 1), blocks);

            input.add(cr);
        }

        return input;
    }

    public static void main(String[] args) {
        ArrayList<ConditionRecord> input = parseInput();
        System.out.println(input.stream().mapToLong(cr -> calculateArrangements(cr)).sum());
        //for (ConditionRecord conditionRecord : input) {
       //     System.out.println(calculateArrangements(conditionRecord));
       // }
    }

}