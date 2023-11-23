public class Cerchio implements Figura {
    //OVERVIEW: classe che descrive la Figura Cerchio. Immutabile

    //attributes
    public final double raggio;

    //constructors
    public Cerchio(double raggio) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Cerchio
        //  lancia IllegalArgumentException se raggio <= 0
        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio non valido");

        this.raggio = raggio;

        assert repOk();
    }

    //methods
    @Override
    public double perimetro() {
        return 2 * raggio * Math.PI;
    }

    @Override
    public String toString() {
        return "Quadrato " + raggio + " - Perimetro: " + perimetro();
    }

    public boolean repOk() {
        if (raggio <= 0)
            return false;

        return true;
    }
    
}
