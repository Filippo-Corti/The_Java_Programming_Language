public abstract class Tratta implements Comparable<Tratta>{
    //OVERVIEW: classe astratta che definisce una Tratta di Viaggio. Immutabile
    
    //attributes
    private final String origine;
    private final String destinazione;
    private final double lunghezza; //in Km
    private final double velocita; //in Km/h

    //constructors
    public Tratta(String origine, String destinazione, double lunghezza, double velocita) throws NullPointerException, IllegalArgumentException{
        //MODIFIES: this
        //EFFECTS: inizializza una nuova Tratta 
        //  lancia NullPointerException se origine o destinazione sono nulli
        //  lancia IllegalArgumentException se origine o destinazione sono vuoti
        //  lancia IllegalArgumentException se lunghezza o velocita sono <= 0
        if (origine == null || destinazione == null)
            throw new NullPointerException("Origine o Destinazione nulla");
        if (origine.equals("") || destinazione.equals(""))
            throw new IllegalArgumentException("Origine o Destinazione vuota");
        if (lunghezza <= 0 || velocita <= 0) 
            throw new IllegalArgumentException("Lunghezza o Velocita non valide");

        this.origine = origine;
        this.destinazione = destinazione;
        this.lunghezza = lunghezza;
        this.velocita = velocita;
    }

    //methods
    public String getOrigine() {
        return origine;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public double getVelocita() {
        return velocita;
    }

    public double getDurata() {
        //EFFECTS: ritorna la durata del percorso
        return this.lunghezza / this.velocita;
    }

    public abstract double getCO2();

    @Override
    public String toString() {
        return "Tratta tra " + origine + " e " + destinazione + "; lunghezza: " + lunghezza + ", velocita: " + velocita;
    }

    @Override
    public int compareTo(Tratta o) {
        return Double.compare(this.getDurata(), o.getDurata());
    }

    //repOk() non necessaria poiché immutabile
    
}
