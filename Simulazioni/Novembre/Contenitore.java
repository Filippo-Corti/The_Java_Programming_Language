public abstract class Contenitore implements Comparable<Contenitore> {
    // OVERVIEW: descrive un Contenitore astratto per una certa quantità di un certo
    // liquido. Comparabile per capienza

    // attributes
    private double quantitaLiquido; // Quantita di Liquido presente
    private String nomeLiquido;

    // constructors
    public Contenitore() {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo contenitore privo di liquido
        nomeLiquido = "";
        quantitaLiquido = 0;

        assert repOk();
    }

    public Contenitore(String nomeLiquido, double quantitaLiquido)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo contenitore con quantitaLiquido unità di
        // nomeLiquido
        // lancia NullPointerException se nomeLiquido è nullo
        // lancia IllegalArgumentException se quantitaLiquido < 0 oppure nomeLiquido è
        // vuoto
        if (nomeLiquido == null)
            throw new NullPointerException("Nome Liquido nullo");

        if (nomeLiquido.equals("") && quantitaLiquido == 0)
            throw new IllegalArgumentException("Nome Liquido vuoto per un contenitore non vuoto");

        if (quantitaLiquido < 0)
            throw new IllegalArgumentException("Quantita Liquido negativa");


        this.nomeLiquido = nomeLiquido;
        this.quantitaLiquido = quantitaLiquido;

        assert repOk();
    }

    // methods
    public abstract double getVolume();

    public double getQuantitaLiquido() {
        return quantitaLiquido;
    }

    public String getNomeLiquido() {
        return nomeLiquido;
    }

    public void travasa(Contenitore c) throws NullPointerException, IncompatibleLiquidsException {
        // MODIFIES: this, c
        // EFFECTS: versa il contenuto di c in this. Se this si riempie prima che si
        // versi tutto il contenuto di c, la quantità di contenuto rimanente resta in c
        // lancia NullPointerException se c è nullo
        // lancia IncompatibleLiquidsException se c.nomeLiquido != this.nomeLiquido
        if (c == null)
            throw new NullPointerException("Contenitore nullo");

        if (!c.nomeLiquido.equals(this.nomeLiquido))
            throw new IncompatibleLiquidsException("Liquidi non compatibili");

        this.quantitaLiquido += c.quantitaLiquido;

        double excess = this.quantitaLiquido - this.getVolume();
        if (excess > 0) {
            this.quantitaLiquido = this.getVolume();
            c.quantitaLiquido = excess;
        } else {
            c.nomeLiquido = "";
            c.quantitaLiquido = 0;
        }

        assert repOk();
    }

    @Override
    public String toString() {
        return "(capienza: " + this.getVolume() + ", contenuto: " + this.quantitaLiquido + ""
                + (this.nomeLiquido.equals("") ? ")" : ", liquido: " + this.nomeLiquido + ")");
    }

    public boolean repOk() {
        if (nomeLiquido == null)
            return false;

        if (quantitaLiquido < 0)
            return false;

        if (quantitaLiquido > 0 && nomeLiquido.equals(""))
            return false;
        
        if (quantitaLiquido > getVolume())
            return false;
        
        return true;
    }

    @Override
    public int compareTo(Contenitore o) {
        return Double.compare(o.getVolume(), this.getVolume());
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    protected abstract Object clone();

    

}
