import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20bis {

    public final static boolean HIGH = true;
    public final static boolean LOW = false;
    public final static boolean ON = true;
    public final static boolean OFF = false;

    public static Queue<QueueItem> queue = new LinkedList<>();
    public static long countLows = 0;
    public static long countHighs = 0;

    public static class QueueItem {

        Module sender;
        Module receiver;
        boolean pulse;

        public QueueItem(Module sender, Module receiver, boolean pulse) {
            this.sender = sender;
            this.receiver = receiver;
            this.pulse = pulse;
        }

        @Override
        public String toString() {
            return "QueueItem [module=" + receiver + ", pulse=" + pulse + "]";
        }

    }

    public static abstract class Module {

        String name;
        ArrayList<Module> neighbours;

        public Module(String name) {
            this.neighbours = new ArrayList<>();
            this.name = name;
        }

        public void addNeighbour(Module m) {
            neighbours.add(m);
        }

        @Override
        public String toString() {
            return " " + name + ": [neighbours=" + neighbours.size() + "]";
        }

        public abstract void receivePulse(boolean pulse, Module sender);

        public void sendToNeighbours(boolean pulse) {
            if (new ArrayList<String>(Arrays.asList("bt", "dl", "fr", "rv")).contains(name) && pulse == HIGH)
                System.out.println(name + " sends " + pulse);

            for (Module m : neighbours) {
                if (m.name.equals("rx") && (((ConjunctionModule)this).inputingModules).values().stream().reduce(true, (a, b) -> a || b)){
                    System.out.println("Pressed RX " + (((ConjunctionModule)this).inputingModules).values());
                }
                /*
                 * if (m.name.equals("rx") && pulse == LOW) {
                 * System.out.println("Highs:" + countHighs);
                 * System.out.println("Low:" + countLows);
                 * System.out.println(countHighs * countLows);
                 * System.out.println("FINITOOOO");
                 * System.exit(0);
                 * }
                 */
                // System.out.println(this.name + " - " + pulse + " --> " + m.name);
                queue.add(new QueueItem(this, m, pulse));
                if (pulse)
                    countHighs++;
                else
                    countLows++;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Module other = (Module) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

    }

    public static class FlipFlopModule extends Module {

        boolean state = OFF; // False = off, True = on

        public FlipFlopModule(String name) {
            super(name);
        }

        @Override
        public void receivePulse(boolean pulse, Module sender) {
            if (pulse == HIGH) // High Pulse
                return;

            if (state == OFF) {
                sendToNeighbours(HIGH);
            } else {
                sendToNeighbours(LOW);
            }
            state = !state;
        }

        @Override
        public String toString() {
            return "FlipFlopModule: " + super.toString();
        }

    }

    public static class ConjunctionModule extends Module {

        HashMap<Module, Boolean> inputingModules = new HashMap<>();

        public ConjunctionModule(String name) {
            super(name);
        }

        public void addInputer(Module m) {
            inputingModules.put(m, LOW);
        }

        @Override
        public void receivePulse(boolean pulse, Module sender) {
            inputingModules.replace(sender, !pulse, pulse);

            boolean reduce = inputingModules.values().stream().reduce(HIGH, (curr, p) -> curr && p);

            if (reduce == HIGH) {
                sendToNeighbours(LOW);
            } else {
                sendToNeighbours(HIGH);
            }
        }

        @Override
        public String toString() {
            return "ConjunctionModule: " + super.toString();
        }

    }

    public static class BroadcasterModule extends Module {

        public BroadcasterModule(String name) {
            super(name);
        }

        @Override
        public void receivePulse(boolean pulse, Module sender) {
            sendToNeighbours(pulse);
        }

        @Override
        public String toString() {
            return "BroadcasterModule: " + super.toString();
        }

    }

    public static ArrayList<Module> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Module> modules = new ArrayList<>();

        // First Create all modules
        while (s.hasNextLine()) {
            String newMod = s.nextLine().split(" -> ")[0];

            switch (newMod.charAt(0)) {
                case 'b':
                    modules.add(new BroadcasterModule(newMod));
                    break;
                case '%':
                    modules.add(new FlipFlopModule(newMod.substring(1)));
                    break;
                case '&':
                    modules.add(new ConjunctionModule(newMod.substring(1)));
                    break;
                default:
            }
        }

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // Then link them up
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" -> ");
            String name = (in[0].charAt(0) == 'b') ? "broadcaster" : in[0].substring(1);
            Module m = getModuleByName(modules, name);

            String[] neighbours = in[1].split(", ");
            for (int i = 0; i < neighbours.length; i++) {
                int x = i;
                Module n = getModuleByName(modules, neighbours[x]);
                if (n == null) {
                    modules.add(new FlipFlopModule(neighbours[x]));
                    n = getModuleByName(modules, neighbours[x]);
                }
                m.addNeighbour(n);
                if (n instanceof ConjunctionModule) {
                    ((ConjunctionModule) n).addInputer(m);
                }
            }
        }

        return modules;
    }

    public static void main(String[] args) {
        /*ArrayList<Module> modules = parseInput();
       // System.out.println(modules);

        int i = 1;
        while (i < 764002) {
            queue.add(new QueueItem(null, getModuleByName(modules, "broadcaster"), LOW));
            countLows++;
            while (!queue.isEmpty()) {
                QueueItem qi = queue.remove();
                qi.receiver.receivePulse(qi.pulse, qi.sender);
            }
            System.out.println(i++);
        }
        System.out.println("Highs:" + countHighs);
        System.out.println("Low:" + countLows);
        System.out.println(countHighs * countLows);*/

        
         ArrayList<Integer> cycles = new ArrayList<>();
         cycles.add(3739);
         cycles.add(4001);
         cycles.add(3943);
         cycles.add(3821);
         long mcm = cycles.get(0);
         for (int i = 1; i < cycles.size(); i++) {
            mcm = mcm(mcm, cycles.get(i));
         }
         System.out.println(mcm);

    }

    public static Module getModuleByName(ArrayList<Module> modules, String name) {
        Object[] found = modules.stream().filter((mod) -> mod.name.equals(name)).toArray();
        if (found.length == 0)
            return null;
        return (Module) found[0];
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

}
