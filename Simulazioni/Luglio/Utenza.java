public abstract class Utenza implements Comparable<Utenza>, Cloneable{
    // OVERVIEW: classe astratta che descrive un'utenza telefonica
    // Non istanziabile poiché non ha modo di stabilire se l'utente può chiamare o meno

    // attributes
    private final String numero;
    private final String nome;
    private int secondiDiConversazione;

    // constructors
    public Utenza(String numero, String nome) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova utenza
        // lancia NullPointerException se numero o nome sono nulli
        // lancia IllegalArgumentException se numero non è una stringa numerica a 10
        // cifre
        // lancia IllegalArgumentException se nome è vuoto
        if (numero == null)
            throw new NullPointerException("Numero nullo");
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");
        try {
            long numeroInt = Long.parseLong(numero);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Numero non valido");
        }
        if (numero.length() != 10)
            throw new IllegalArgumentException("Numero non valido");

        this.nome = nome;
        this.numero = numero;
        this.secondiDiConversazione = 0;

        assert repOk();
    }

    // methods
    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public int getSecondiDiConversazione() {
        return secondiDiConversazione;
    }

    public abstract void chiama(int secondi) throws IllegalArgumentException, TempoEsauritoException;

    protected void incrementaSecondiDiConversazione(int secondi) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: incrementa i secondi totali di conversazione di secondi
        //  lancia IllegalArgumentException se secondi < 0
        if (secondi < 0)
            throw new IllegalArgumentException("Secondi non validi");
        this.secondiDiConversazione += secondi;

        assert repOk();
    } 

    @Override
    public String toString() {
        return "Utenza di " + nome + ", numero: " + numero + "; durata: " + secondiDiConversazione;
    }

    public boolean repOk() {
        //Il resto è final (lo controllo solo nel costruttore)
        if (secondiDiConversazione < 0)
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Utenza))
            return false;
        Utenza other = (Utenza) obj;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    @Override
    public int compareTo(Utenza o) {
        return Integer.compare(secondiDiConversazione, o.secondiDiConversazione);
    }

    @Override
    protected Object clone() {
        Utenza u = null;
        try {
            u = (Utenza) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return u;
    }

}
