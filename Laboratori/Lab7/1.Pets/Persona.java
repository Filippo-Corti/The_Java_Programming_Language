import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Persona implements Iterable<Pet> {
    //OVERVIEW: classe che descrive una Persona, con i suoi Animali Domestici
    //  iterabile sui suoi animali

    //attributes
    private final String nome;
    private ArrayList<Pet> animaliDomestici;

    //constructors
    public Persona(String nome) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza una nuova persona con un nome e senza animali
        //  lancia NullPointerExceptions se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        
        if (nome.equals(""))   
            throw new IllegalArgumentException("Nome vuoto");

        this.nome = nome;
        this.animaliDomestici = new ArrayList<>();

        assert repOk();
    }

    //methods

    public String getNome() {
        return nome;
    }

    public void insert(Pet p) throws NullPointerException {
        //MODIFIES: this
        //EFFECTS: inserisce p in this.animaliDomestici
        //  lancia NullPointerException se p è nullo
        if (p == null)
            throw new NullPointerException("P nullo");
        
        animaliDomestici.add(p);
        
        assert repOk();
    }
    public void remove(Pet p) throws NullPointerException, NoSuchElementException {
        //MODIFIES: this
        //EFFECTS: rimouve p da this.animaliDomestici
        //  lancia NullPointerException se p è nullo
        // lancia NoSuchElementException se p non è in this
        if (p == null)
            throw new NullPointerException("P nullo");

        if (!animaliDomestici.contains(p)) 
            throw new NoSuchElementException("P non tra gli animali domestici di " + this.nome);

        animaliDomestici.remove(p);    

        assert repOk();
    }

    public void dirigiCoro() {
        System.out.println("Il coro degli animali di " + nome + ":");
        for (Pet p : this)
            p.verso();
    }

    @Override
    public Iterator<Pet> iterator() {
        //EFFECTS: restituisce un iterator per iterare sugli animali domestici di this
        return new Iterator<>() {

            Iterator<Pet> i = animaliDomestici.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Pet next() {
                return i.next();
            }

        };
    }

    @Override
    public String toString() {
        return nome + ": " + animaliDomestici.toString();
    }

    public boolean repOk() {
        if (nome == null)
            return false;

        if (nome.equals(""))  
            return false;
        
        if (animaliDomestici == null)
            return false;
        
        for (Pet pet : animaliDomestici) {
            if (pet == null)
                return false;
        }

        return true;
    }



}
