import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int annoRiferimento = 0;
        try {
            annoRiferimento = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Parametro da riga di comando mancante o errato");
        }

        ArrayList<Dottore> dipendenti = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci dipendenti nel formato `nome codice anno salario` (termina con CTRL+D)");
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            try {
                dipendenti.add(new Dottore(in[0], in[1], Integer.parseInt(in[3]), Integer.parseInt(in[2])));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input nel formato non corretto");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Dipendenti assunti prima del " + annoRiferimento);
        for (Dottore dipendente : dipendenti) {
            if (dipendente.getAnnoAssuzione() < annoRiferimento)
                System.out.println(dipendente.getNome());
        }
    }

}
