public class Cono extends Solido {
    //OVERVIEW: classe che descrive un Cono. Estende Solido. Immutabile

    //attributes
    private final double raggio;  
    private final double altezza;     

    //constructors
    public Cono(double raggio, double altezza) throws IllegalArgumentException{
        //MODIFIES: this
        //EFFECTS: inizializza cono con raggio e altezza
        //  lancia IllegalArgumentException se raggio o altezza <= 0
        super("cono");
        
        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio non positivo");
        
        if (altezza <= 0)
            throw new IllegalArgumentException("Altezza non positiva");

        this.raggio = raggio;
        this.altezza = altezza;

        assert repOk();
    }

    public double getRaggio() {
        return raggio;
    }

    public double getAltezza() {
        return altezza;
    }

    @Override
    public double volume() {
        //EFFECTS: ritorna il volume di this
        return Math.PI * altezza * raggio * raggio / 3;
    } 

    @Override
    public String toString() {
        return "Cono di raggio " + raggio + " altezza " + altezza;
    }

    public boolean repOk() {
        if (raggio <= 0)
            return false;
            
        if (altezza <= 0)
            return false;
    
        return true;
    }

}
