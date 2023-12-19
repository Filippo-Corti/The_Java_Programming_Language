import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19bis {

    static class Interval {
        int a, b; // [a, b)

        public Interval(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int size() {
            return b - a;
        }

        @Override
        public String toString() {
            return "Interval [a=" + a + ", b=" + b + "]";
        }

    }

    static class MachineSystem {
        HashMap<String, Workflow> workflows;
        ArrayList<ArrayList<Interval>> intervals;

        public MachineSystem(HashMap<String, Workflow> workflows, ArrayList<ArrayList<Interval>> intervals) {
            this.workflows = workflows;
            this.intervals = intervals;
        }

        public boolean workOn(Piece piece) {
            String label = "in";
            while (true) {
                label = workflows.get(label).applyFlow(piece);
                if (label.equals("A")) {
                    return true;
                } else if (label.equals("R")) {
                    return false;
                }
            }
        }

        @Override
        public String toString() {
            return "MachineSystem [workflows=" + workflows + ", intervals=" + intervals + "]";
        }

    }

    static class Piece {
        int x, m, a, s;

        public Piece(int x, int m, int a, int s) {
            this.x = x;
            this.m = m;
            this.a = a;
            this.s = s;
        }

        public int get(char c) {
            switch (c) {
                case 'x':
                    return x;
                case 'm':
                    return m;
                case 'a':
                    return a;
                case 's':
                    return s;
            }
            return 0;
        }

        public int sum() {
            return x + m + a + s;
        }

        @Override
        public String toString() {
            return "Piece [x=" + x + ", m=" + m + ", a=" + a + ", s=" + s + "]";
        }

    }

    static class Workflow {

        ArrayList<Function<Piece, String>> rules;

        @Override
        public String toString() {
            return "Workflow [rules=" + rules + "]";
        }

        public String applyFlow(Piece piece) {
            for (Function<Piece, String> function : rules) {
                String out = function.apply(piece);
                if (!out.equals("")) {
                    return out;
                }
            }
            return null;
        }

    }

    public static MachineSystem parseInput() {
        ArrayList<ArrayList<Integer>> intervals = new ArrayList<>();
        intervals.add(new ArrayList<>());
        intervals.add(new ArrayList<>());
        intervals.add(new ArrayList<>());
        intervals.add(new ArrayList<>());

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        HashMap<String, Workflow> workflows = new HashMap<>();

        while (true) {
            String in = s.nextLine();
            if (in.equals(""))
                break;

            Workflow wf = new Workflow();

            ArrayList<Function<Piece, String>> rules = new ArrayList<>();

            String label = in.substring(0, in.indexOf("{"));

            String[] rulesString = in.substring(in.indexOf("{") + 1, in.indexOf("}")).split(",");

            for (int i = 0; i < rulesString.length; i++) {
                String rule = rulesString[i];
                Function<Piece, String> ruleFunc = createRuleFunction(rule, intervals);
                rules.add(ruleFunc);
            }

            wf.rules = rules;
            workflows.put(label, wf);
        }
        return new MachineSystem(workflows, convertToIntervals(intervals));
    }

    private static ArrayList<ArrayList<Interval>> convertToIntervals(ArrayList<ArrayList<Integer>> ints) {

        for (ArrayList<Integer> l : ints) {
            l.sort(null);
        }

        ArrayList<ArrayList<Interval>> intervalsList = new ArrayList<>();

        int a = 1, b = 0;
        for (int i = 0; i < ints.size(); i++) {
            ArrayList<Integer> list = ints.get(i);
            ArrayList<Interval> intervals = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                b = list.get(j);
                intervals.add(new Interval(a, b));
                a = b;
            }
            a = 1;
            intervals.add(new Interval(b, 4001));
            intervalsList.add(intervals);
        }

        return intervalsList;

    }

    private static Function<Piece, String> createRuleFunction(String rule, ArrayList<ArrayList<Integer>> intervals) {

        Matcher m = Pattern.compile("[0-9]+").matcher(rule);
        String nextLabel = (rule.contains(":")) ? rule.substring(rule.indexOf(":") + 1) : rule;
        if (m.find()) {
            int n = Integer.parseInt(m.group());
            int n2 = n;
            Function<Piece, String> f = null;
            if (rule.contains("<")) {
                f = (p) -> (p.get(rule.charAt(0)) < n) ? nextLabel : "";
            } else {
                f = (p) -> (p.get(rule.charAt(0)) > n) ? nextLabel : "";
                n2++;
            }
            switch (rule.charAt(0)) {
                case 'x':
                    intervals.get(0).add(n2);
                    break;
                case 'm':
                    intervals.get(1).add(n2);
                    break;
                case 'a':
                    intervals.get(2).add(n2);
                    break;
                case 's':
                    intervals.get(3).add(n2);
                    break;
            }
            return f;
        } else {
            return (p) -> nextLabel;
        }

    }

    public static void main(String[] args) {
        MachineSystem input = parseInput();
        BigInteger s = new BigInteger("0");

        int i = 0;
        for (Interval xs : input.intervals.get(0)) {
            for (Interval ms : input.intervals.get(1)) {
                for (Interval as : input.intervals.get(2)) {
                    for (Interval ss : input.intervals.get(3)) {
                        Piece p = new Piece(xs.a, ms.a, as.a, ss.a);
                        if (input.workOn(p)) {
                            s = s.add(BigInteger.valueOf(
                                    (long) xs.size() * (long) ms.size() * (long) as.size() * (long) ss.size()));
                        }
                    }
                }
            }
            System.out.println(i++ + " / " + input.intervals.get(0).size());
        }
        System.out.println(s);
    }

}
