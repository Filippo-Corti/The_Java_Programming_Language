public class Decorazione implements Cloneable {
    // OVERVIEW: classe che descrive una decorazione natalizia. Immutabile

    // attributes
    private final String nome;
    private final double peso;

    // constructors
    public Decorazione(String nome, double peso) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova decorazione con nome e peso
        // lancia NullPointerException se nome è nullo
        // lancia IllegalArgumentException se nome è vuoto o se peso <= 0
        if (nome == null)
            throw new NullPointerException("Nome nullo");

        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");

        if (peso <= 0)
            throw new IllegalArgumentException("Peso <= 0");

        this.nome = nome;
        this.peso = peso;
    }

    // methods
    public double getPeso() {
        return peso;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Decorazione: " + nome + "; peso: " + peso;
    }

    @Override
    public Object clone() {
        Decorazione d = null;
        try {
            d = (Decorazione) super.clone();
        } catch (CloneNotSupportedException e) {
            d = new Decorazione(nome, peso);
        }
        return d;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Decorazione other = (Decorazione) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
            return false;
        return true;
    }

    

}
