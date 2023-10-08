public class SommaProdotti {

    public static void main(String[] args) {
        int somma = 0;
        for (int i = 0; i < args.length; i += 2) {
            somma += Integer.parseInt(args[i]) * Integer.parseInt(args[i + 1]);
        }
        System.out.println("La somma e' " + somma);
    }

    // Assolutamente non necessario
    public static int calcola(int[] n) {
        int somma = 0;
        for (int i = 0; i < n.length; i += 2) {
            somma += n[i] * n[i + 1];
        }
        return somma;
    }
}
