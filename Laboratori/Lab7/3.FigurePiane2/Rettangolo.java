public class Rettangolo implements Figura {
    //OVERVIEW: classe che descrive la Figura Rettangolo. Immutabile

    //attributes
    public final double base, altezza;

    //constructors
    public Rettangolo(double base, double altezza) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Rettangolo
        //  lancia IllegalArgumentException se base o altezza <= 0
        if (base <= 0 || altezza <= 0)
            throw new IllegalArgumentException("Lati non validi");

        this.base = base;
        this.altezza = altezza;

        assert repOk();
    }

    //methods
    @Override
    public double perimetro() {
        return (base + altezza) * 2;
    }

    @Override
    public String toString() {
        return "Rettangolo " + base + " " + altezza + " - Area: " + area() + " - Perimetro: " + perimetro();
    }

    public boolean repOk() {
        if (base <= 0 || altezza <= 0)
            return false;

        return true;
    }

    @Override
    public double area() {
        return base * altezza;
    }
    
}
