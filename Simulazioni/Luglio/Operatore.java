import java.util.ArrayList;
import java.util.Iterator;

public class Operatore implements Iterable<Utenza> {
    //OVERVIEW: classe che descrive un Operatore telefonico con le proprie utenze

    //attributes
    private final String nome;
    private ArrayList<Utenza> utenze = new ArrayList<>();

    //constructors
    public Operatore(String nome) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Operatore
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");
        
        this.nome = nome;
    }

    //methods
    public void add(Utenza u) throws NullPointerException, UtenzaNonValidaException {
        //MODIFIES: this
        //EFFECTS: inserisce u in this.
        //  lancia NullPointerException se u è nullo
        //  lancia UtenzaNonValidaExceptions e u è già in this
        if (u == null) 
            throw new NullPointerException("Utenza nulla");
        if (utenze.contains(u))
            throw new UtenzaNonValidaException("Utenza gia presente");
        
        utenze.add((Utenza)u.clone());

        assert repOk();
    }
    
    public Utenza getByNumero(String numero) throws NullPointerException, UtenzaNonValidaException {
        //EFFECTS: restituisce l'utenza con numero indicato, se presente
        //  lancia NullPointerException se numero è nullo
        //  lancia UtenzaNonValidaException u non è in this
        for (Utenza utenza : utenze) {
            if (utenza.getNumero().equals(numero)) 
                return (Utenza)utenza.clone(); //Ci vuole il Clone qua altrimenti sta roba non ha senso
        } 

        throw new UtenzaNonValidaException("Utenza non presente");

    }

    @Override
    public Iterator<Utenza> iterator() {
        //EFFECTS: ritorna un Iteratore sulle Utenze, in ordine naturale
        return new Iterator<Utenza>() {

            Iterator<Utenza> i = getSortedIterator();

            private Iterator<Utenza> getSortedIterator() {
                ArrayList<Utenza> utenzeOrdinate = new ArrayList<>(utenze);
                utenzeOrdinate.sort(null);
                return utenzeOrdinate.iterator();
            }

            @Override
            public boolean hasNext() {
               return i.hasNext();
            }

            @Override
            public Utenza next() {
                return i.next();    
            }
            
        };
    }

    public boolean repOk() {
        if (utenze == null)
            return false;
        for (Utenza utenza : utenze) {
            if (utenza == null)
                return false;
            for (Utenza utenza2 : utenze) {
                if (utenza.equals(utenza2))
                    return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        String res =  "Utenze di " + nome + ":\n";  
        for (Utenza utenza : this) {
            res += "\t" + utenza + "\n";
        }
        return res;
    }


    

    
}
