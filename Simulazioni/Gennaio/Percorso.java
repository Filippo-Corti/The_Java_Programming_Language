import java.util.ArrayList;
import java.util.Iterator;

public class Percorso implements Iterable<Tratta>{
    //OVERVIEW: classe che descrive un Percorso come sequenza di Tratte.
    //  Iterabile sulle sue Tratte

    //attributes
    private ArrayList<Tratta> tratte = new ArrayList<>();

    //constructors
    public Percorso() {
        //MODIFIES: this
        //EFFECTS: inizializza un Percorso vuoto
     }

    public Percorso(Percorso p) throws NullPointerException, TrattaNonValidaException {
        for (Tratta tratta : p) {
            this.add(tratta);
        }
    }

    //methods
    public void add(Tratta t) throws NullPointerException, TrattaNonValidaException {
        //MODIFIES: this
        //EFFECTS: aggiunge t a this
        //  lancia NullPointerException se t è nullo
        //  lancia TrattaNonValidaException se t.origine != ultimaTrattaAggiunta.destinazione
        if (t == null)
            throw new NullPointerException("Tratta nulla");

        if (!tratte.isEmpty() && !tratte.get(tratte.size() - 1).getDestinazione().equals(t.getOrigine()))
            throw new TrattaNonValidaException("Tratta non continua il percorso");

        tratte.add(t);

        assert repOk();
    }

    public double getDurata() {
        //EFFECTS: ritorna la durata complessiva di this
        return tratte.stream().mapToDouble(Tratta::getDurata).sum();
    }

    public double getCO2() {
        //EFFECTS: ritorna la CO2 emessa complessivamente da this
        return tratte.stream().mapToDouble(Tratta::getCO2).sum();
    }

    public ArrayList<Tratta> sort() {
        //EFFECTS: ritorna le tratte in this, ordinate per durata
        // ATTENZIONE: le tratte ordinate non è detto che formino necessariamente un Percorso valido
        ArrayList<Tratta> tratteSorted = new ArrayList<>(tratte);
        tratteSorted.sort(null);
        return tratteSorted;
    }

    public boolean repOk() {
        if (tratte == null)
            return false;

        for (int i = 0; i < tratte.size(); i++) {
                if (tratte.get(i) == null)
                    return false;
                if (i != 0 && !tratte.get(i - 1).getDestinazione().equals(tratte.get(i).getOrigine()))
                    return false;
        }  

        return true;
    }

    @Override
    public String toString() {
        String res = "Percorso (durata: " + getDurata() + ", co2: " + getCO2() + ")\n";
        for (Tratta tratta : this) {
            res += "\t" + tratta + "\n";
        }
        return res;
    }

    @Override
    public Iterator<Tratta> iterator() {
        //EFFECTS: restituisce un iteratore sulle Tratte di this
        return new Iterator<Tratta>() {

            Iterator<Tratta> i = tratte.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Tratta next() {
                return i.next();
            }
            
        };
    }



}
