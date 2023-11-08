import java.util.ArrayList;
import java.util.Iterator;

/* Esempio di Classe che implementa l'interfaccia Iterable */

public class Studente implements Iterable<Esame> {
    // OVERVIEW: classe mutabile che descrive uno studente con i suoi esami
    //   + un'altra ArrayList a caso 

    // attributes
    String nome;
    ArrayList<Esame> esami;
    ArrayList<Integer> altroIterabile; 

    // constructors
    public Studente(String nome) throws IllegalArgumentException {
        if (nome == null || nome.equals(""))
            throw new IllegalArgumentException("Nome non valido");

        this.nome = nome;
        esami = new ArrayList<>();
        altroIterabile = new ArrayList<>();

        assert repOk();
    }

    public boolean repOk() {
        if (nome == null || nome.equals(""))
            return false;
        //...

        return true;
    }

    public void addEsame(Esame e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("Esame non valido");

        esami.add((Esame) e.clone());

        assert repOk();
    }

    public void addNumero(int i) {
        altroIterabile.add(i);
    }

    @Override
    public String toString() {
        String res = "Studente " + this.nome + "(";

        for (Esame esame : esami) {
            res += "\t\t\t" + esame.getNome() + ": " + esame.getVoto() + "\n";
        }

        return res + ")";
    }

    @Override
    public Iterator<Esame> iterator() {
        //EFFECTS: restituisce un iteratore che produce gli esami superati dallo studente
        // REQUIRES : this non deve essere modificato mentre l’iteratore e’ in uso
        return esami.iterator();
    }

    public Iterator<Integer> altroIterator() {
        return altroIterabile.iterator();
    }

    public static void main(String[] args) {
        Studente s = new Studente("Filippo");

        s.addEsame(new Esame("Programmazione I", 28));
        s.addEsame(new Esame("Matenatica del Continuo", 20));
        s.addEsame(new Esame("Interazione Uomo-Macchina", 24));
        s.addEsame(new Esame("Architettura degli Elaboratori", 30));

        s.addNumero(17);
        s.addNumero(981);

        /* ITERAZIONE SU ITERATOR PRINCIPALE */

        for (Esame esame : s) { //Iterazione direttamente sullo studente per i suoi esami (Incredibile)
            System.out.println(esame);
        }

        
        /* ITERAZIONE SU ALTRO ITERATOR */

        Iterator<Integer> i = s.altroIterator();

        while (i.hasNext()) {
            System.out.println(i.next());
        }

        

    }

}