public class Asteroide {
    //OVERVIEW: classe mutabile che descrive un asteroide

    double massa; //in kg
    double distanza; //dalla terra, in km

    //constructors
    public Asteroide(double massa, double distanza) {
        this.massa = massa;
        this.distanza= distanza;
    }

    //methods
    public double getMassa() {
        return massa;
    }

    public double getDistanza() {
        return distanza;
    }

    @Override
    public String toString() {
        return "Asteroide a distanza " + distanza + "km dal peso di " + massa + "kg";
    }

    public double getForzaGravitazionale() {
        //EFFECTS: ritorna la forza gravitazionale esercitata tra l'asteroide e la terra
        return this.massa/(this.distanza*this.distanza);
    }


}
