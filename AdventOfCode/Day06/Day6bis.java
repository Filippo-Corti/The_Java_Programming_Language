import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6bis {

    static class Gara {
        long time;
        long recordDistance;

        public Gara(long time) {
            this.time = time;
        }

        public Gara(long time, long recordDistance) {
            this.time = time;
            this.recordDistance = recordDistance;
        }

        @Override
        public String toString() {
            return "Gara [time=" + time + ", recordDistance=" + recordDistance + "]";
        }

        public long getTime() {
            return time;
        }

        public long getRecordDistance() {
            return recordDistance;
        }

        
        
    }

    public static Gara parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
           System.out.println("File not found");
        }

        String times = "";
        String[] in = s.nextLine().split(": ");
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(in[1]);
        while(m.find()) {
            times += m.group();
        }

        String distances = "";
        String[] in2 = s.nextLine().split(": ");
        m = p.matcher(in2[1]);
        int i = 0;
        while(m.find()) {
            distances += m.group();
        }

        return new Gara(Long.parseLong(times), Long.parseLong(distances));
    }



    public static int getWaysToWin(Gara g) {
        double x1 = (((-g.time) + Math.sqrt((long)g.time*g.time + 4 * (-(g.recordDistance + 0.0001)))) / -2);
        double x2 = (((-g.time) - Math.sqrt((long)g.time*g.time + 4 * (-(g.recordDistance + 0.0001)))) / -2);

        return (int) (Math.floor(x2) - Math.ceil(x1) + 1);
    }

    public static void main(String[] args) {
        Gara gara = parseInput();
        System.out.println(getWaysToWin(gara));
    }
}
