import java.util.Scanner;

public class Statistiche {

    public static void main(String[] args) {
        int lines = 0;
        System.out.println("Inserisci un testo su piu' righe (termina con Ctrl+D)");
        String testo = "";

        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            testo += s.nextLine() + " ";
            lines++;
        }

        int words = testo.split(" +").length;
        double totalLen = testo.length() - words + 1;
        System.out.println(testo);

        System.out.println("Statistiche:");
        System.out.println("Numero linee: " + lines);
        System.out.println("Numero parole: " + words);
        System.out.println("Lunghezza media: " + totalLen / words);
    }

}
