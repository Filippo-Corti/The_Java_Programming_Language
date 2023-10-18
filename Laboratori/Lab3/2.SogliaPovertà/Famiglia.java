import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Famiglia {
    // OVERVIEW: classe mutabile che modella una famiglia ed il suo reddito

    int dimensione;
    double reddito;

    // constructors
    public Famiglia(int dimensione, double reddito) throws InputMismatchException {
        if (dimensione <= 0)
            throw new InputMismatchException("Impossibile creare famiglia con meno di 1 componente");
        this.dimensione = dimensione;
        this.reddito = reddito;
    }

    // methods
    public boolean sottoSogliaPoverta(double costoCasa, double costoCibo) throws InputMismatchException {
        // EFFECTS: ritorna true se this è sotto la soglia della povertà, false
        // altrimenti
        // lancia InputMismatchException se i due parametri non sono entrambi >= 0
        if (costoCasa < 0)
            throw new InputMismatchException("Costo Casa non può essere negativo");

        if (costoCibo < 0)
            throw new InputMismatchException("Costo Cibo non può essere negativo");

        return costoCasa + costoCibo * this.dimensione > this.reddito * 0.5;
    }

    @Override
    public String toString() {
        return dimensione + " persone con reddito complessivo di " + reddito;
    }

    public static void main(String[] args) {
        double costoVitto = 0, costoAlloggio = 0;

        if (args.length < 2)
            System.out.println("Inserire due parametri da riga di comando");

        try {
            costoVitto = Double.parseDouble(args[0]);
            costoAlloggio = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Parametri non validi");
        }

        Scanner s = new Scanner(System.in);
        ArrayList<Famiglia> famiglie = new ArrayList<>();

        System.out.println("Inserisci il reddito e la dimensione di una famiglia (Ctrl+D per terminare la lettura)");
        while (s.hasNext()) {
            double red = s.nextDouble();
            int dim = s.nextInt();
            try {
                famiglie.add(new Famiglia(dim, red));
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            System.out
                    .println("Inserisci il reddito e la dimensione di una famiglia (Ctrl+D per terminare la lettura)");
        }

        System.out.println();

        int i = 1;
        try {
            for (Famiglia f : famiglie) {
                if (f.sottoSogliaPoverta(costoAlloggio, costoVitto))
                    System.out.println("Famiglia " + i + ": " + f);
                i++;
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }

}
