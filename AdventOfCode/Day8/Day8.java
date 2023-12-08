import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {

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

            Matcher matcher = Pattern.compile("[A-Z]+").matcher(in);

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
    
    public static int part1(Map m) {
        int c = 0;
        String node = "AAA";
        while(!node.equals("ZZZ")) {
            if(m.directions.charAt(c % m.directions.length()) == 'L') {
                node = m.map.get(node).left;
            } else {
                node = m.map.get(node).right;
            }
            c++;
            //System.out.println(c + " - Node: " + node);
        }
        return c;
    }


    public static void main(String[] args) {
        Map m = parseInput();

        System.out.println(part1(m));

    }
}