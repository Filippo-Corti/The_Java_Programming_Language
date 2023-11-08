public class Piatto {
    // OVERVIEW: classe non mutabile che rappresenta il Piatto di
    // un Menu. E' anche in grado di restituire un piatto uguale, ma con prezzo modificato

    // attributes
    private String nome;
    private String tipo; // primo o secondo
    private int costo;

    // costructors
    public Piatto(String nome, String tipo, int costo) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: istanza un oggetto Piatto, con attributi specificati
        // lancia NullPointerException se nome o tipo sono nulli
        // lancia IllegalArgumentException se nome è vuoto, se tipo non è nè primo nè
        // secondo o se costo <= 0
        if (nome == null || tipo == null)
            throw new NullPointerException("Parametro nullo non valido");

        if (nome.equals(""))
            throw new IllegalArgumentException("Parametro vuoto nome non valido");

        if (!(tipo.equals("primo") || tipo.equals("secondo")))
            throw new IllegalArgumentException("Tipo non riconosciuto (primo/secondo)");

        if (costo <= 0)
            throw new IllegalArgumentException("Costo non valido (>0)");

        this.nome = nome;
        this.tipo = tipo;
        this.costo = costo;

        assert repOk();
    }

    // methods

    public boolean repOk() {
        if (nome == null || tipo == null)
            return false;

        if (nome.equals(""))
            return false;

        if (!tipo.equals("primo") || !tipo.equals("secondo"))
            return false;

        if (costo <= 0)
            return false;

        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCosto() {
        return costo;
    }

    public Piatto copiaPiatto(int costo) throws IllegalArgumentException {
        // EFFECTS: ritorna un nuovo Piatto con costo = costo e gli altri parametri
        // uguali a this.
        // lancia IllegalArgumentException se costo <= 0
        if (costo <= 0)
            throw new IllegalArgumentException("Costo non valido (>0)");

        return new Piatto(this.nome, this.tipo, costo);
    }

    @Override
    public String toString() {
        return this.nome + " " + this.tipo + " " + this.costo + "E";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piatto other = (Piatto) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (costo != other.costo)
            return false;
        return true;
    }

    @Override
    public Object clone() {
        Piatto p = null;
        try {
            p = (Piatto) super.clone();
        } catch (CloneNotSupportedException e) {
            p = new Piatto(nome, tipo, costo);
            // Ha solo parametri non mutabili quindi non ho bisogno di clonare nulla qua
        }
        return p;
    }

}
