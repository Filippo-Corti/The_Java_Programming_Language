import java.util.ArrayList;
import java.util.Iterator;

public class Contatto {
    // OVERVIEW: classe mutabile che modella un Contatto, elemento di una rubrica.
    // Il Contatto può avere memorizzati più numeri di telefono e più email.
    // Entrambi gli insiemi sono iterabili
    // Nome e Cognome non sono modificabili dopo l'inizializzazione del Contatto

    private final String nome, cognome; // Nome e Cognome non modificabili
    private ArrayList<String> numeriDiTelefono;
    private ArrayList<String> indirizziEmail;

    // constructors
    public Contatto(String nome, String cognome) throws IllegalArgumentException {
        // EFFECTS: istanzia un nuovo Contatto
        // lancia IllegalArgumentExceptions se nome o cognome sono null o vuoti
        if (nome == null || nome.equals("") || cognome == null || cognome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");

        this.nome = nome;
        this.cognome = cognome;
        this.numeriDiTelefono = new ArrayList<>();
        this.indirizziEmail = new ArrayList<>();

        assert repOk();
    }

    // methods

    public boolean repOk() {
        if (nome == null || nome.equals(""))
            return false;

        if (cognome == null || cognome.equals(""))
            return false;

        if (numeriDiTelefono == null)
            return false;

        for (String numero : numeriDiTelefono) {
            if (numero == null)
                return false;
        }

        if (indirizziEmail == null)
            return false;

        for (String email : indirizziEmail) {
            if (email == null)
                return false;
        }

        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void addNumeroDiTelefono(String numeroDiTelefono) throws IllegalArgumentException {
        if (numeroDiTelefono == null || numeroDiTelefono.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.numeriDiTelefono.add(numeroDiTelefono);

        assert repOk();
    }

    public void removeNumeroDiTelefono(String numeroDiTelefono) throws IllegalArgumentException {
        if (numeroDiTelefono == null || numeroDiTelefono.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.numeriDiTelefono.remove(numeroDiTelefono);

        assert repOk();
    }

    public void addIndirizzoEmail(String email) throws IllegalArgumentException {
        if (email == null || email.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.indirizziEmail.add(email);

        assert repOk();
    }

    public void removeIndirizzoEmail(String email) throws IllegalArgumentException {
        if (email == null || email.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.indirizziEmail.remove(email);

        assert repOk();
    }

    public Contatto copiaContatto(String nome, String cognome) throws IllegalArgumentException {
        // EFFECTS: restituisce un nuovo Contatto, clone di this in tutto e per tutto
        // eccetto per il nuovo nome e cognome
        // lancia IllegalArgumentException se i parametri sono vuoti o nulli
        if (nome == null || nome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        if (cognome == null || cognome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");

        Contatto c = new Contatto(nome, cognome);

        for (String numero : numeriDiTelefono) {
            c.addIndirizzoEmail(numero);
        }

        for (String email : indirizziEmail) {
            c.addIndirizzoEmail(email);
        }

        return c;
    }

    public Iterator<String> iteratorNumeri() {
        // EFFECTS: restituisce un iterator che scorre l'elenco dei numeri di telefono
        // associati al Contatto
        return new Iterator<String>() {

            Iterator<String> i = numeriDiTelefono.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public String next() {
                return i.next();
            }

        };
    }

    public Iterator<String> iteratorEmail() {
        // EFFECTS: restituisce un iterator che scorre l'elenco delle email
        // associate al Contatto
        return new Iterator<String>() {

            Iterator<String> i = indirizziEmail.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public String next() {
                return i.next();
            }

        };
    }

    @Override
    public String toString() {
        String res = nome + " " + cognome;
        if (numeriDiTelefono.size() != 0) {
            res += "\n\ttel: ";
            for (String numero : numeriDiTelefono) {
                res += numero + " ";
            }
        }
        if (indirizziEmail.size() != 0) {
            res += "\n\te-mail: ";
            for (String email : indirizziEmail) {
                res += email + " ";
            }
        }
        return res;
    }
}
