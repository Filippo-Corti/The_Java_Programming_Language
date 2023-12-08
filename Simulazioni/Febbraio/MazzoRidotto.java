import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MazzoRidotto implements Iterator<Carta> {
    // OVERVIEW: classe che descrive un Mazzo di 40 carte (senza figure)

    // attributes
    protected ArrayList<Carta> mazzo = new ArrayList<>();

    // constructors
    public MazzoRidotto() {
        // MODIFIES: this
        // EFFECTS: inizializza un Mazzo Ridotto
        for (int i = 1; i <= 10; i++) {
            for (Seme seme : Seme.values()) {
                mazzo.add(new Carta(i, seme));
            }
        }

        assert repOk();
    }

    // methods
    public void add(Carta c) throws CardNotValidException, CardExistsException {
        // MODIFIES: this
        // EFFECTS: inserisce c in this.
        // lancia CardNotValidException se c è nulla o non è valida per this
        // lancia CardExistsException se c è già in this
        if (!isValidCard(c))
            throw new CardNotValidException("Carta non valida");

        if (mazzo.contains(c))
            throw new CardExistsException("Carta gia nel mazzo");

        mazzo.add(c);

        assert repOk();
    }

    public static boolean isValidCard(Carta c) {
        // EFFECTS: ritorna true se c è una carta valida. False altrimenti
        return (c != null) && (c.getValore() >= 1 && c.getValore() <= 10);
    }

    public void shuffle() {
        // MODIFIES: this
        // EFFECTS: mischia this
        Collections.shuffle(mazzo);
    }

    public void sort() { 
        //MODIFIES: this
        //EFFECTS: ordina this
        mazzo.sort(new Comparator<Carta>() {
            @Override
            public int compare(Carta o1, Carta o2) {
                if (o1.getSeme() != o2.getSeme())
                    return o1.getSeme().compareTo(o2.getSeme());
                return Integer.compare(o1.getValore(), o2.getValore());
        }});
    }

    @Override
    public boolean hasNext() {
        return !mazzo.isEmpty();
    }

    @Override
    public Carta next() {
        // MODIFIES: this
        // EFFECTS: estrae e restituisce una carta dal mazzo;
        //  lancia NoSuchElementException se this è vuoto
        if (!hasNext())
            throw new NoSuchElementException("Mazzo vuoto");
        return mazzo.remove(0);
    }

    public boolean repOk() {
        if (mazzo == null)
            return false;
        
        for (Carta carta : mazzo) {
            if (!isValidCard(carta))
                return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        String res = "MazzoRidotto: \n";
        for (Carta carta : mazzo) {
            res += "\t" + carta + "\n";
        }
        return res;
    }

}
