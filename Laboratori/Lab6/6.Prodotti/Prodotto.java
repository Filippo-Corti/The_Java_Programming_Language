public class Prodotto {
    //OVERVIEW: Classe che descrive un prodotto

    //attributes
    public final String nome;
    private double costo;
    private static final double PERCENTUALE_SCONTO_DEFAULT = 5.00;


    //constructors
    public Prodotto(String nome, double costo) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Prodotto, con nome e prezzo
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto o se costo è < 0
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        
        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");
        
        if (costo < 0)
            throw new IllegalArgumentException("Costo negativo");
        
        this.nome = nome;
        this.costo = costo;

        assert repOk();
    }

    //methods
    public String getNome() { //Messo solo per consistenza (nome è già public)
        return nome;
    }


    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECST: modifica costo di this. 
        //  lancia IllegalArgumentException se costo < 0
        if (costo < 0)
        throw new IllegalArgumentException("Costo negativo");

        this.costo = costo;

        assert repOk();
    }

    public double sconto() {
        //EFFECTS: ritorna this.costo, scontato di percentualeSeScontato%
        return costo - costo * PERCENTUALE_SCONTO_DEFAULT / 100;
    }

     public double sconto(double percentuale) throws IllegalArgumentException {
        //EFFECTS: ritorna this.costo, scontato di percentuale%
        //  lancia IllegalArgumentException se percentuale < 0
        if (percentuale < 0)
            throw new IllegalArgumentException("Sconto negativo");

        return costo - costo * percentuale / 100;
    }

    public double getPercentualeSconto() {
        return PERCENTUALE_SCONTO_DEFAULT;
    }


    @Override
    public String toString() {
        return "Prodotto " + nome + " con prezzo " + costo + "€";
    }

    public boolean repOk() {
        if (nome == null || nome.equals(""))
            return false;

        if (costo < 0)
            return false;
        
        return true;
    }

}