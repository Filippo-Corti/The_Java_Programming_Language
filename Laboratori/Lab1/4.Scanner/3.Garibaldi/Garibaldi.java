import java.util.Scanner;

public class Garibaldi {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String text = "";
        System.out.println("Inserisci un testo su piu' righe (termina con Ctrl+D)");
        while (s.hasNext()) {
            text += s.nextLine() + "\n";
        }
        System.out.println("Risultato trasformazione:");
        System.out.println(convertiInGaribaldi(text));
    }

    private static String convertiInGaribaldi(String s) {
        char[] vocali = {'a', 'e', 'i', 'o', 'A', 'E', 'I', 'O'};

        for (char vocale : vocali) {
            s = s.replace("" + vocale, "u");
        }
        return s;
    }

}
