public class Quadrato implements Figura {
    //OVERVIEW: classe che descrive la Figura Quadrato. Immutabile

    //attributes
    public final double lato;

    //constructors
    public Quadrato(double lato) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Quadrato
        //  lancia IllegalArgumentException se lato <= 0
        if (lato <= 0)
            throw new IllegalArgumentException("Lato non valido");

        this.lato = lato;

        assert repOk();
    }

    //methods
    @Override
    public double perimetro() {
        return lato * 4;
    }

    @Override
    public String toString() {
        return "Quadrato " + lato + " - Perimetro: " + perimetro();
    }

    public boolean repOk() {
        if (lato <= 0)
            return false;

        return true;
    }
    
}
