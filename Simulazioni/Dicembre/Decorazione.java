public class Decorazione {
    //OVERVIEW: classe che descrive una decorazione natalizia

    //attributes
    public final String nome;
    public final double peso;

    //constructors
    public Decorazione(String nome, double peso) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza una nuova decorazione con nome e peso
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto o se peso <= 0
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        
        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");
    
        if (peso <= 0)
            throw new IllegalArgumentException("Peso <= 0");

        this.nome = nome;
        this.peso = peso;
    }

    //methods

    @Override
    public String toString() {
        return "Decorazione: " + nome + "; peso: " + peso;
    }
    
    public double getPeso() {
        return peso;
    }
    
    @Override
    public Decorazione clone() {
        Decorazione d =  new Decorazione(nome, peso);
        return d;
    }

}
