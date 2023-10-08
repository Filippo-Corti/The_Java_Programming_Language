import java.util.ArrayList;

public class Pitagorica {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        stampaTavolaPitagorica(creaTavolaPitagorica(n));
    }

    public static ArrayList<ArrayList<Integer>> creaTavolaPitagorica(int n) {
        ArrayList<ArrayList<Integer>> tavola = new ArrayList<ArrayList<Integer>>();

        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> rigaTavola = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                rigaTavola.add(i * j);
            }
            tavola.add(rigaTavola);
        }
        return tavola;
    }

    public static void stampaTavolaPitagorica(ArrayList<ArrayList<Integer>> s) {
        for (ArrayList<Integer> riga : s) {
            for (int x : riga) {
                System.out.printf("%6d", x);
            }
            System.out.println();
        }
    }

}
