import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {

    static class Gara {
        int time;
        int recordDistance;

        public Gara(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Gara [time=" + time + ", recordDistance=" + recordDistance + "]";
        }
        
    }


    
    public static ArrayList<Gara> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
           System.out.println("File not found");
        }

        ArrayList<Gara> gare = new ArrayList<>();
        String[] in = s.nextLine().split(": ");
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(in[1]);
        while(m.find()) {
            gare.add(new Gara(Integer.parseInt(m.group())));
        }

        String[] in2 = s.nextLine().split(": ");
        m = p.matcher(in2[1]);
        int i = 0;
        while(m.find()) {
            gare.get(i++).recordDistance = Integer.parseInt(m.group());
        }

        return gare;
    }

    public static int getWaysToWin(Gara g) {
        double x1 = (((-g.time) + Math.sqrt(g.time*g.time - 4 * (-1) * (-(g.recordDistance + 0.0001))))/ 2 * (-1));
        double x2 = (((-g.time) - Math.sqrt(g.time*g.time - 4 * (-1) * (-(g.recordDistance + 0.0001))))/ 2 * (-1));

        return (int) (Math.floor(x2) - Math.ceil(x1) + 1);
    }

    public static void main(String[] args) {
        ArrayList<Gara> gare = parseInput();
        //System.out.println(getWaysToWin(gare.get(0)));
        //System.out.println(getWaysToWin(gare.get(1)));
        //System.out.println(getWaysToWin(gare.get(2)));
        System.out.println(gare.stream().mapToInt((g) -> getWaysToWin(g)).reduce(1, (m, el) -> m * el));
    }
}
