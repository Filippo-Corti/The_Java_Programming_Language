import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pretendenti implements Iterable<Integer> {
    // OVERVIEW: classe che modella il metodo di selezione dei pretendenti della
    // Principessa Eva

    // attributes
    ArrayList<Integer> pretendenti;

    // constructors
    public Pretendenti(int n) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inzializza this con n pretendenti
        // lancia IllegalArgumentException se n <= 0
        if (n <= 0)
            throw new IllegalArgumentException("Numero di pretendenti non valido");

        pretendenti = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            pretendenti.add(i);
        }

        assert repOk();
    }

    // methods

    public int getVincitore() throws PretendentiStillRunningException {
        // EFFECTS: restituisce il vincitore della conta
        // lancia PretendentiStillRunningException se i pretendenti rimasti sono più di
        // 1

        if (pretendenti.size() != 1)
            throw new PretendentiStillRunningException("C'e' ancora piu' di un pretendente");

        return pretendenti.get(0);
    }

    @Override
    public String toString() {
        String res = "Pretendenti: ";
        for (Integer integer : pretendenti) {
            res += integer + " ";
        }
        return res;
    }

    public boolean repOk() {

        if (pretendenti == null)
            return false;

        if (pretendenti.size() == 0)
            return false;

        Integer prev = null;

        for (int pretendente : pretendenti) {
            if (pretendente <= 0)
                return false;

            if (prev != null && pretendente <= prev)
                return false;

            prev = pretendente;
        }

        return true;
    }

    @Override
    public Iterator<Integer> iterator() {
        // EFFECTS: restituisce un iterator che seleziona un nuovo pretendente a
        // distanza 3 dall'ultimo restituito. Quando si arriva in coda o in testa
        // la conta continua cambiando direzione
        // MODIFIES: può rimuovere l'ultimo elemento selezionato di Pretendenti.this

        return new Iterator<Integer>() {

            int current = 0; // indice dell'elemento da cui devo partire a contare
            boolean dir; // true = crescente, false = decrescente
            boolean removed = true; // se ho chiamato remove() devo prima chiamare next() e poi richiamarla

            @Override
            public boolean hasNext() {
                return pretendenti.size() > 1;
            }

            @Override
            public Integer next() {
                // MODIFIES: this (Iterator)
                // EFFECTS: restituisce il prossimo pretendente, aggiorna l'elemento corrente e
                // la direzione, imposta removed = false
                // se non ci sono altri pretendenti da rimuovere lancia NoSuchElementException

                if (!hasNext())
                    throw new NoSuchElementException("Non ci sono altri pretendenti da eliminare");

                this.current += 2 * (this.dir ? 1 : -1);

                int lastIndex = pretendenti.size() - 1;
                if (this.current >= lastIndex) {
                    this.current = 2 * lastIndex - this.current; // lastIndex - (this.current - lastIndex);
                    this.dir = false;
                }
                if (this.current <= 0) {
                    this.current = -this.current;
                    this.dir = true;
                }

                removed = false;
                return pretendenti.get(this.current);
            }

            public void remove() {
                // MODIFIES: Pretendenti.this, this
                // EFFECTS: rimuove l'ultimo elememnto restituito da next() e aggiorna elemento
                // corrente. Mette poi removed = true
                // lancia IllegalStateException se l'ultima remove() è stata chiamata più
                // recentemente dell'ultima next()
                if (this.removed)
                    throw new IllegalStateException("Elemento gia' rimosso");

                pretendenti.remove(current);

                int lastIndex = pretendenti.size() - 1;

                if (!this.dir)
                    this.current--;

                if (this.current > lastIndex) {
                    this.current--;
                    this.dir = false;
                }

                if (this.current == 0)
                    this.dir = true;

                removed = true;
            }

            @Override
            public String toString() {
                return "Si conta da " + current + " direzione " + (this.dir ? "avanti" : "indietro");
            }

        };
    }

    public static void main(String[] args) {
        Pretendenti pretendenti = new Pretendenti(Integer.parseInt(args[0]));

        Iterator<Integer> iteraPretendenti = pretendenti.iterator();

        while (iteraPretendenti.hasNext()) {
            //System.out.println(pretendenti);
            //System.out.println(iteraPretendenti);
            System.out.println("Eliminato: " + iteraPretendenti.next());
            iteraPretendenti.remove();
        }
        try {
            System.out.println("Il numero " + pretendenti.getVincitore() + " è il fortunato vincitore");
        } catch (PretendentiStillRunningException e) {
            //Impossibile
            e.printStackTrace();
        }
    }

}