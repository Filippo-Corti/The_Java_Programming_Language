import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

public class Day5 {

    static class Input {
    
        ArrayList<Map> maps;
        ArrayList<Long> seeds;

        public Input(ArrayList<Map> maps, ArrayList<Long> seeds) {
            this.maps = maps;
            this.seeds = seeds;
        }


    }

    static class MapEntry {

        long start;
        long range;
        long destStart;

        public MapEntry(long start, long range, long destStart) {
            this.start = start;
            this.range = range;
            this.destStart = destStart;
        }

        @Override
        public String toString() {
            return "MapEntry [start=" + start + ", range=" + range + ", destStart=" + destStart + "]";
        }

        

    }

    static class Map {

        ArrayList<MapEntry> entries;
        
        public Map(ArrayList<MapEntry> entries) {
            this.entries = entries;
        }

        @Override
        public String toString() {
            return "Map [entries=" + entries + "]";
        }

        public long map(long x) {
            for (MapEntry mapEntry : entries) {
                if (x >= mapEntry.start && x < mapEntry.start + mapEntry.range)
                    return x + (mapEntry.destStart - mapEntry.start);
            }
            return x;
        }

        
    }  


    public static Input parseInput() {

        ArrayList<Map> maps = new ArrayList<>();
        ArrayList<Long> seeds = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        String[] seedsString = s.nextLine().split(": ")[1].split(" ");

        for (String string : seedsString) {
            seeds.add(Long.parseLong(string));
        }

        s.nextLine(); //Empty line
        s.nextLine(); //Seed-to-oil Map

        ArrayList<MapEntry> m = new ArrayList<>();
        while (s.hasNextLine()) {

            String in = s.nextLine();
            if (in.equals("")) {
                maps.add(new Map(m));
                m = new ArrayList<>();
                s.nextLine(); //Empty line
            } else {

                String[] fields = in.split(" ");

                m.add(new MapEntry(Long.parseLong(fields[1]), Long.parseLong(fields[2]), Long.parseLong(fields[0])));

            }
        }

        maps.add(new Map(m));
        return new Input(maps, seeds);
    }

    public static long mapThroughAll(ArrayList<Map> ms, long x) {
        long res = x;
        for (Map map : ms) {
            res = map.map(res);
        }
        return res;
    }

    public static void main(String[] args) {
        Input input = parseInput();

        System.out.println(input.seeds.stream().map((x) -> mapThroughAll(input.maps, x)).min((o1, o2) -> Long.compare(o1, o2)));

    }

}