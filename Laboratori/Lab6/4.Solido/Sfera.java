public class Sfera extends Solido {
    //OVERVIEW: classe che descrive una Sfera. Estende Solido. Immutabile

    //attributes
    private final double raggio;    

    //constructors
    public Sfera(double raggio) throws IllegalArgumentException{
        //MODIFIES: this
        //EFFECTS: inizializza sfera con raggio
        //  lancia IllegalArgumentException se raggio <= 0
        super("sfera");

        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio non positivo");

        this.raggio = raggio;

        assert repOk();
    }

    public double getRaggio() {
        return raggio;
    }

    @Override
    public double volume() {
        //EFFECTS: ritorna il volume di this
        return (4/3)*Math.PI*Math.pow(raggio, 3); 
    } 

    @Override
    public String toString() {
        return "Sfera di raggio " + raggio;
    }

    public boolean repOk() {
        if (raggio <= 0)
            return false;
    
        return true;
    }

}
