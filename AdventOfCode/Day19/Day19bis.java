import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19bis {

    static class MachineSystem {
        HashMap<String, Workflow> workflows;

        public MachineSystem(HashMap<String, Workflow> workflows) {
            this.workflows = workflows;
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
            return "MachineSystem [workflows=" + workflows + "]";
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
                Function<Piece, String> ruleFunc = createRuleFunction(rule);
                rules.add(ruleFunc);
            }

            wf.rules = rules;
            workflows.put(label, wf);
        }

        return new MachineSystem(workflows);
    }

    private static Function<Piece, String> createRuleFunction(String rule) {

        Matcher m = Pattern.compile("[0-9]+").matcher(rule);
        String nextLabel = (rule.contains(":")) ? rule.substring(rule.indexOf(":") + 1) : rule;
        if (m.find()) {
            int n = Integer.parseInt(m.group());
            if (rule.contains("<")) {
                return (p) -> (p.get(rule.charAt(0)) < n) ? nextLabel : "";
            } else {
                return (p) -> (p.get(rule.charAt(0)) > n) ? nextLabel : "";
            }
        } else {
            return (p) -> nextLabel;
        }

    }

    public static void main(String[] args) {
        MachineSystem input = parseInput();
        int s = 0;
        for (int i = 1; i <= 4000; i++) {
            for (int j = 1; j <= 4000; j++) {
                for (int k = 1; k <= 4000; k++) {
                    for (int l = 1; l <= 4000; l++) {
                        Piece p = new Piece(i, j, k, l);
                        if (input.workOn(p))
                            s++;
                    }
                }
                System.out.println(j);
            }
            System.out.println("+1/4000");
        }
        System.out.println(s);
    }

}
