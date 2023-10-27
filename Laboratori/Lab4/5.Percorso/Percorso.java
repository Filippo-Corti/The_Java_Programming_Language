import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Percorso {
    // OVERVIEW: classe mutabile che descrive una sequenza di punti Punto

    // attributes
    ArrayList<Punto> punti;

    // constructors
    public Percorso() {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova istanza di Percorso, senza punti
        punti = new ArrayList<>();

        assert repOk();
    }

    // methods
    public int getSize() {
        //EFFECTS: ritorna punti.size()
        return punti.size();
    }

    public void addPunto(Punto p) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: aggiunge p alla sequenza di punti di this
        // lancia NullPointerException se p è nullo
        if (p == null)
            throw new NullPointerException("Punto nullo");

        punti.add(p);

        assert repOk();
    }

    public void removePunto() throws PercorsoVuotoException {
        // MODIFIES: this
        // EFFECTS: rimuove dalla sequenza l'ultimo punto in coda
        // lancia PercorsoVuotoException se this non ha nessun punto in coda
        if (punti.size() == 0)
            throw new PercorsoVuotoException("Percorso vuoto. Impossibile rimuovere punti");

        punti.remove(punti.size() - 1);

        assert repOk();
    }

    public double distanzaTraPunti(int i, int j) throws IllegalArgumentException {
        // EFFECTS: ritorna la distanza totale del percorso a partire dall' i-esimo
        // Punto fino al j-esimo (incluso).
        // lancia IllegalArgumentException se i > j oppure se i e j non appartengono al
        // range [0, size(punti)-1]
        if (i < 0 || j < 0 || i > punti.size() || j > punti.size() || i > j)
            throw new IllegalArgumentException("Indici non validi");

        double totalDist = 0;
        for (int k = i; k < j; k++) {
            totalDist += punti.get(k).distanza(punti.get(k + 1));
        }

        return totalDist;
    }

    public double lunghezzaTotale() {
        // EFFECTS: ritorna la lunghezza totale della sequenza di punti
        return distanzaTraPunti(0, punti.size() - 1);
    }

    public boolean repOk() {
        if (punti == null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        String res = "Percorso (\n";
        for (Punto punto : punti) {
            res += "\t\t" + punto.toString() + "\n";
        }
        return res + ")";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Percorso percorso = new Percorso();
        
        System.out.println("Inserisci le coordinate di un punto per riga nel formato <x y> (termina con CTRL+D)");
        System.out.println("ATTENZIONE: Inserire valori decimali utilizzando la virgola e non il punto");

        while (s.hasNext()) {
            try {
                percorso.addPunto(new Punto(s.nextDouble(), s.nextDouble()));
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Input errato. Punto non inserito");
            }
        }

        for (int i = 0; i < percorso.getSize() - 1; i++) {
            System.out.println("Tratto " + (i+1) + ": distanza " + percorso.distanzaTraPunti(i, i+1));
        }

        System.out.println("Totale: " + percorso.lunghezzaTotale());
        
    }

}
