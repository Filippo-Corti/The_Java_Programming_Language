import java.util.Scanner;

public class Farfallino {

    public static void main(String[] args) {
        String testo = "";
        System.out.println("Inserisci un testo su piu' righe (termina con Ctrl+D)");
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            testo += s.nextLine() + "\n";
        }

        System.out.println("Risultato:");
        System.out.print(convertiInFarfallino(testo));
    }

    private static String convertiInFarfallino(String s) {
        char[] vocaliMinuscole = { 'a', 'e', 'i', 'o', 'u'}; //Non mi sembra una grande idea ma non trovo alternative. Non so nemmeno come fare per le lettere accentate.
        char[] vocaliMaiuscole = {'A', 'E', 'I', 'O', 'U'};
        for (char vocale : vocaliMinuscole) {
            s = s.replace("" + vocale, vocale + "f" + vocale);
        }
        for (char vocale : vocaliMaiuscole) {
            s = s.replace("" + vocale, vocale + "F" + vocale);
        }
        return s;
    }

    

}
