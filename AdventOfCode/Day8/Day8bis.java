import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8bis {

    static class Tuple {

        String left, right;

        public Tuple(String left, String right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Tuple [left=" + left + ", right=" + right + "]";
        }

    }

    static class Map {

        String directions;
        HashMap<String, Tuple> map;

        public Map(String directions, HashMap<String, Tuple> map) {
            this.directions = directions;
            this.map = map;
        }

        public Map() {
        }

        @Override
        public String toString() {
            return "Map [directions=" + directions + ", map=" + map + "]";
        }

    }

    public static Map parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Map m = new Map();

        m.directions = s.nextLine();

        s.nextLine(); // Empty line

        HashMap<String, Tuple> map = new HashMap<>();

        while (s.hasNext()) {
            String in = s.nextLine();

            Matcher matcher = Pattern.compile("[1-9A-Z]+").matcher(in);

            matcher.find();
            String key = matcher.group();

            matcher.find();
            String left = matcher.group();
            matcher.find();
            String right = matcher.group();

            Tuple t = new Tuple(left, right);

            map.put(key, t);
        }

        m.map = map;

        return m;
    }

    public static long part2(Map m) {
        String[] nodes = getStartingNodes(m);

        ArrayList<Integer> zFound = new ArrayList<>();

        for (String node : nodes) {
            int c = 0;
            String start = node;

            int foundZ = 0;

            while (!node.equals(start) || c == 0) {
                if (m.directions.charAt(c % m.directions.length()) == 'L') {
                    node = m.map.get(node).left;
                } else {
                    node = m.map.get(node).right;
                }

                if (node.charAt(2) == 'Z') {
                    if (foundZ == 0) {
                        foundZ = c;
                    } else {
                        zFound.add(c - foundZ);
                        break;
                    }
                }
                c++;
            }
        }
        System.out.println(zFound);

        long mcm = zFound.get(0);

        for (int i = 1; i < zFound.size(); i++) {
            mcm = mcm(mcm, zFound.get(i));
        }

        return mcm;
    }

    static long mcm(long a, long b) {
        return (a * b) / mcd(a, b);
    }

    static long mcd(long a, long b) {
        long t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private static boolean endingPoint(String[] nodes) {
        for (String s : nodes) {
            if (s.charAt(2) != 'Z') {
                return false;
            }
        }
        return true;
    }

    private static String[] getStartingNodes(Map m) {

        ArrayList<String> sn = new ArrayList<>();

        for (String keyString : m.map.keySet()) {
            if (keyString.charAt(2) == 'A') {
                sn.add(keyString);
            }
        }

        Object[] objects = sn.toArray();

        return Arrays.copyOf(objects, objects.length, String[].class);
    }

    public static void main(String[] args) {
        Map m = parseInput();

        System.out.println(part2(m));

    }
}