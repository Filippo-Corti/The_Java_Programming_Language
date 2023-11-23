public class Triangolo implements Figura {
    // OVERVIEW: classe che descrive la Figura Triangolo generico. Immutabile

    // attributes
    public final double lato1, lato2, lato3;

    // constructors
    public Triangolo(double lato1, double lato2, double lato3)
            throws ImpossibleTriangleException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo Triangolo
        // lancia IllegalArgumentException se uno dei lati è <= 0
        // lancia ImpossibleTriangleException se i 3 lati non formano un triangolo
        if (lato1 <= 0 || lato2 <= 0 || lato3 <= 0)
            throw new IllegalArgumentException("Lato non valido");

        if (lato1 + lato2 <= lato3 || lato1 + lato3 <= lato2 || lato2 + lato3 <= lato1)
            throw new ImpossibleTriangleException("I 3 lati non formano un triangolo");

        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;

        assert repOk();
    }

    // methods
    @Override
    public double perimetro() {
        return lato1 + lato2 + lato3;
    }

    @Override
    public String toString() {
        return "Triangolo " + lato1 + " " + lato2 + " " + lato3 + " - Area: " + area() + " - Perimetro: " + perimetro();
    }

    public boolean repOk() {
        if (lato1 <= 0 || lato2 <= 0 || lato3 <= 0)
            return false;

        if (lato1 + lato2 <= lato3 || lato1 + lato3 <= lato2 || lato2 + lato3 <= lato1)
            return false;

        return true;
    }

    @Override
    public double area() {
            double s = perimetro()/2; //semiperimetro
            return Math.sqrt(s * (s - lato1) * (s - lato2) * (s - lato3)); //Formula di Erone
    }

}
