import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AlberoNatalizio implements Iterable<Decorazione>{
    // OVERVIEW: classe che descrive un Albero Natalizio con le sue decorazioni
    // Iterabile sulle decorazioni

    // attributes
    private ArrayList<Decorazione> decorazioni = new ArrayList<>();
    private final double caricoMassimo;
    private final double potenzaMassima;

    // constructors
    public AlberoNatalizio(double caricoMassimo, double potenzaMassima) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializa this
        // lancia IllegalArgumentException se caricoMassimo <= 0 oppure potenzaMassima
        // <= 0
        if (caricoMassimo <= 0)
            throw new IllegalArgumentException("Carico Massimo <= 0");

        if (potenzaMassima <= 0)
            throw new IllegalArgumentException("Potenza Massima <= 0");

        this.caricoMassimo = caricoMassimo;
        this.potenzaMassima = potenzaMassima;
    }

    // methods
    public void add(Decorazione d) throws NullPointerException, TopperExistsException, WeightReachedException {
        // MODIFIES: this
        // EFFECTS: aggiunge d a this
        // lancia NullPointerException se d è nullo
        // lancia TopperExistsException se d è un Puntale ed esiste già un puntale in
        // this
        // lancia WeightReachedException se la somma dei pesi delle decorazioni è
        // maggiore di caricoMassimo
        if (d == null)
            throw new NullPointerException("Decorazione nulla");
        
        if (d instanceof Puntale && contaPuntali() > 0)
            throw new TopperExistsException("Puntale già aggiunto");

        if (getSommaPesi() + d.getPeso() > caricoMassimo)
            throw new WeightReachedException("Carico superato");
        
        decorazioni.add((Decorazione) d.clone());

        assert repOk();
    }

    public void remove(Decorazione d) throws NullPointerException, NoSuchElementException {
        // MODIFIES: this
        // EFFECTS: rimuove d da this
        // lancia NullPointerException se d è nullo
        // lancia NoSuchElementException se d non è in this
        if (d == null)
            throw new NullPointerException("Decorazione nulla");

        if (!decorazioni.remove(d))
            throw new NoSuchElementException("Decorazione non presente");
        
        assert repOk();
    }

    public void accendi() {
        // MODIFIES: this
        // EFFECTS: accende le decorazioni elettriche di this dalla meno alla più
        // richiedente. Si ferma quando raggiunge la potenzaMassima di this

        ArrayList<DecorazioneElettrica> decorazioniElettriche = new ArrayList<>(decorazioni.stream().filter(t -> t instanceof DecorazioneElettrica).map(t -> (DecorazioneElettrica)t).toList());

        decorazioniElettriche.forEach(t -> t.spegni()); //Spegni tutte le decorazioni

        decorazioniElettriche.sort(null); //Ordina le decorazioni per potenza

        double carico = 0;
        for (int i = 0; i < decorazioniElettriche.size(); i++) {
            if (carico + decorazioniElettriche.get(i).getPotenza() < caricoMassimo) //Accendi fin tanto che è possibile
                decorazioniElettriche.get(i).accendi();
            else 
                break;
        }
    }

    public double getSommaPesi() {
        //EFFECTS: ritorna la somma dei pesi delle decorazioni dell'albero
        return decorazioni.stream().mapToDouble(Decorazione::getPeso).reduce(0, (s, el) -> s + el);
    }

    public double getSommaPotenze() {
        //EFFECTS: ritorna la somma delle potenze delle decorazioni accese dell'albero
        return decorazioni.stream().filter(t -> ((t instanceof DecorazioneElettrica) && ((DecorazioneElettrica) t).isAccesa())).map(t -> (DecorazioneElettrica)t).mapToDouble(DecorazioneElettrica::getPotenza).reduce(0, (s, el) -> s + el);
        //E' la cosa più bella che abbia mai avuto l'onore di scrivere ^^^
    }

    private double contaPuntali() {
        int c = 0;
        for (Decorazione decorazione : decorazioni) {
            if (decorazione instanceof Puntale)
                c++;
        }
        return c;
    }

    public boolean repOk() {
        if (decorazioni == null)
            return false;

        for (Decorazione decorazione : decorazioni) {
            if (decorazione == null)
                return false;
        }

        if (contaPuntali() > 1)
            return false;

        if (getSommaPesi() > caricoMassimo)
            return false;

        if (getSommaPotenze() > potenzaMassima)
            return false;

        return true;
    }

    @Override
    public Iterator<Decorazione> iterator() {
        return new Iterator<Decorazione>() {

            Iterator<Decorazione> i = decorazioni.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();    
            }

            @Override
            public Decorazione next() {
                return i.next();
            }

        }; 
    }

    @Override
    public String toString() {
        String res = "Albero (Carico: " + caricoMassimo + ", Potenza: " + potenzaMassima + ")\n"; 
        
        for (Decorazione d : this) {
            res += "\t" + d + "\n";
        }

        return res;
    
    }

}
