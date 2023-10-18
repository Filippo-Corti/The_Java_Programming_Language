public class Auto {
    //OVERIVEW: classe mutabile che descrive un'automobile

    int capacitaSerbatoio;
    double litriInSerbatoio;
    int velocitaMassima;
    int velocitaCorrente;
    double consumoMedio; //km/litro

    //constructors 
    public Auto(int capacitaSerbatoio, int velocitaMassima, double consumoMedio) throws IllegalArgumentException {
        if (capacitaSerbatoio <= 0 || velocitaMassima <= 0 || consumoMedio < 0) 
            throw new IllegalArgumentException("Sono ammessi solo parametri positivi");
        this.capacitaSerbatoio = capacitaSerbatoio;
        this.velocitaMassima = velocitaMassima;
        this.consumoMedio = consumoMedio;
    }

    //methods
    public void riempiSerbatoio(int litri) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: esegue litriInSerbatoio_post += litri
        //     lancia IllegalArgumentiException se litri < 0
        if (litri < 0)
            throw new IllegalArgumentException("Impossibile rimuovere benzina dal serbatoio");

        this.litriInSerbatoio += litri;
    }

    public void consumaLitri(double litri) throws CarburanteInsufficienteException {
        if (this.litriInSerbatoio - litri < 0)
            throw new CarburanteInsufficienteException("Il carburante non è sufficiente a completare il viaggio");
    }

    public void setVelocita(int velocitaCorrente) throws IllegalArgumentException, VelocitaMassimaSuperataException{
        if (velocitaCorrente <= 0) 
            throw new IllegalArgumentException("Non puoi andare a velocita' negativa o nulla");
        
        if (velocitaCorrente >= this.velocitaMassima) 
            throw new VelocitaMassimaSuperataException("L'auto non va così veloce");
        this.velocitaCorrente = velocitaCorrente;
    }

    public double viaggia(double distanza, int velocita) throws IllegalArgumentException, CarburanteInsufficienteException, VelocitaMassimaSuperataException {
        //MODIFIES: this
        //EFFECTS: simula il viaggio dell'auto aggiornando il carburante rimanente e restituendo il tempo necessario a percorrerlo (in ore)
        //  lancia IllegalArgumentException se distanza < 0 o velocità <= 0
        if (distanza < 0)
            throw new IllegalArgumentException("Non puoi percorrere distanza negativa");
        if (velocita <= 0)
            throw new IllegalArgumentException("Non puoi andare a velocita' negativa o nulla");


        double litriConsumati = distanza/this.consumoMedio;


        consumaLitri(litriConsumati);
        setVelocita(velocita);




    }

    

    
    
    
}