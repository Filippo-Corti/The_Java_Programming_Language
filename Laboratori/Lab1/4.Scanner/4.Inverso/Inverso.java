import java.util.Scanner;

public class Inverso {
    
    public static void main(String[] args) {
        String testo = "";
        System.out.println("Inserisci un testo su più righe (termina con riga vuota):");
        
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            testo += s.nextLine() + "\n";
        }

        for (int i = testo.length() - 1; i >= 0; i--) {
            System.out.print(testo.charAt(i));
        }
        System.out.println();
 
    }

}
