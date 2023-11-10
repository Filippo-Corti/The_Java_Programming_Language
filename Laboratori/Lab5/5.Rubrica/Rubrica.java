import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Rubrica implements Iterable<Contatto> {
    // OVERVIEW: classe che rappresenta una Rubrica telefonica come elenco di
    // Contatti
    // Iterabile sui suoi contatti

    // attributes
    private HashMap<String, Contatto> contatti;

    // constructors
    public Rubrica() {
        // MODIFIES: this
        // EFFECTS: istanzia e ritorna una nuova Rubrica, vuota
        contatti = new HashMap<>();

        assert repOk();
    }

    // methods

    public void addContatto(Contatto c) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: aggiunge c alla rubrica
        // lancia NullPointerException se c nullo
        if (c == null)
            throw new NullPointerException("Parametro nullo non valido");

        contatti.put(c.getNome() + " " + c.getCognome(), c);

        assert repOk();

    }

    public void deleteContatto(String nome, String cognome) throws NullPointerException, IllegalArgumentException {
        // MODIFES: this
        // EFFECTS: rimuove il contatto specificato da nome e cognome dalla rubrica
        // lancia NullPointerException se uno dei parametri è nullo
        // lancia IllegalArgumentException se uno dei parametri è vuoto
        if (nome == null || cognome == null)
            throw new NullPointerException("Parametri nulli non validi");

        if (nome.equals("") || cognome.equals(""))
            throw new IllegalArgumentException("Parametri vuoti non validi");

        contatti.remove(nome + " " + cognome);

        assert repOk();
    }

    public void editContatto(String vecchioNome, String vecchioCognome, String nuovoNome, String nuovoCognome)
            throws NullPointerException, IllegalArgumentException, NoSuchElementException {
        // MODIFIES: this
        // EFFECTS: modifica il nome ed il cognome del contatto specificato da
        // <vecchioNome vecchioCognome> in <nuovoNome nuovoCognome>.
        // lancia NullPointerException se uno dei parametri è nullo
        // lancia IllegalArgumentException se uno dei parametri è vuoto
        // lancia NoSuchElementException se il contatto che si vuole modificare non
        // esiste in Rubrica
        if (vecchioNome == null || vecchioCognome == null || nuovoNome == null || nuovoCognome == null)
            throw new NullPointerException("Parametri nulli non validi");

        if (vecchioNome.equals("") || vecchioCognome.equals("") || nuovoNome.equals("") || nuovoCognome.equals(""))
            throw new IllegalArgumentException("Parametri vuoti non validi");

        if (!contatti.containsKey(vecchioNome + " " + vecchioCognome))
            throw new NoSuchElementException("Contatto non in rubrica");

        contatti.replace(vecchioNome + " " + vecchioCognome,
                contatti.get(vecchioNome + " " + vecchioCognome).copiaContatto(nuovoNome, nuovoCognome));

        assert repOk();
    }

    @Override
    public Iterator<Contatto> iterator() {
        // EFFECTS: ritorna un iterator che scorre la lista dei contatti in Rubrica
        return new Iterator<Contatto>() {

            Iterator<Contatto> i = contatti.values().iterator();

			@Override
			public boolean hasNext() {
                return i.hasNext();			
            }

			@Override
			public Contatto next() {
               return i.next();			
			}
            
        };
    }

    @Override
    public String toString() {
        String res = "Rubrica: \n";

        for (Contatto c : contatti.values()) {
            res += "> " + c.toString() + "\n";
        }

        return res;
    }

    public boolean repOk() {
        if (contatti == null)
            return false;

        for (Contatto c : contatti.values()) {
            if (c == null)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        Contatto c1 = new Contatto("Joe", "Henry");
        c1.addNumeroDiTelefono("5553456");
        c1.addNumeroDiTelefono("5134902");
        c1.addIndirizzoEmail("jhenry@bix.net");
        c1.addIndirizzoEmail("j.hen@nis.org");

        Contatto c2 = new Contatto("JimBob", "Johnson");
        c2.addIndirizzoEmail("jbj@email.com");
        c2.addIndirizzoEmail("bjb@gmail.com");

        Contatto c3 = new Contatto("Jack", "Ripper");
        c3.addNumeroDiTelefono("3116535");
        c3.addIndirizzoEmail("slice@dice.org");
        c3.addIndirizzoEmail("dice@slice.org");

        Rubrica rubrica = new Rubrica();

        rubrica.addContatto(c1);
        rubrica.addContatto(c2);
        rubrica.addContatto(c3);

        // 1° modo
        System.out.println(rubrica);
        System.out.println("---------------------------");

        // 2° modo
        Iterator<Contatto> contatti = rubrica.iterator();

        while (contatti.hasNext()) {
            System.out.println(contatti.next());
        }
        System.out.println("---------------------------");

        // 3° modo
        for (Contatto contatto : rubrica) {
            System.out.println(contatto);
        }
        System.out.println("---------------------------");

    }

}
