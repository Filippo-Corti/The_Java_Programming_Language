import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5bis {

    static class Input {
    
        ArrayList<Map> maps;

        public Input(ArrayList<Map> maps) {
            this.maps = maps;
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

        File f = new File("input.txt");
        Scanner s = null;
        try {
             s = new Scanner(f);
        } catch (FileNotFoundException e ){
            System.out.println("File not found");
            System.exit(1);
        }

       s.nextLine(); //Skip the seeds (for the moment)

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
        return new Input(maps);
    }

    public static long mapThroughAll(ArrayList<Map> ms, long x) {
        long res = x;
        for (Map map : ms) {
            res = map.map(res);
        }
        return res;
    }

    public static long part2(ArrayList<Map> maps) {
        File f = new File("input.txt");
        Scanner s = null;
        try {
             s = new Scanner(f);
        } catch (FileNotFoundException e ){
            System.out.println("File not found");
            System.exit(1);
        }

        s.next(); //seeds:

        ArrayList<Long> mins = new ArrayList<>();

        while(s.hasNext()) {
            String in = s.next();
            if (in.equals("seed-to-soil"))
                break; //End of seeds?
            long in1 = Long.parseLong(in);
            long in2 = s.nextLong();


            long min = mapThroughAll(maps, in1);
            for (long i = 1; i < in2; i++) {
                long curr = mapThroughAll(maps, in1 + i);
                if (min > curr)
                    min = curr;
            }

            mins.add(min);
            System.out.println(min);


        }

        return mins.stream().min((o1, o2) -> Long.compare(o1, o2)).get();

    }

    public static void main(String[] args) {
        Input input = parseInput();
        System.out.println(part2(input.maps));

    }

}