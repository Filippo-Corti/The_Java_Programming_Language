import java.util.Scanner;

public class Auto {
    // OVERIVEW: classe mutabile che descrive un'automobile

    int capacitaSerbatoio;
    double litriInSerbatoio;
    int velocitaMassima;
    int velocitaCorrente;
    double consumoMedio; // km/litro

    // constructors
    public Auto(int capacitaSerbatoio, int velocitaMassima, double consumoMedio) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova istanza di Auto
        // lancia IllegalArgumentException se capacitaSerbatoio <= 0, velocitaMassima
        // <=0 o consumoMedio < 0
        if (capacitaSerbatoio <= 0 || velocitaMassima <= 0 || consumoMedio < 0)
            throw new IllegalArgumentException("Sono ammessi solo parametri positivi");
        this.capacitaSerbatoio = capacitaSerbatoio;
        this.velocitaMassima = velocitaMassima;
        this.consumoMedio = consumoMedio;
    }

    // methods

    public double getLitriInSerbatoio() {
        return litriInSerbatoio;
    }

    public void riempiSerbatoio(double litri) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: esegue litriInSerbatoio_post = litriInSerbatoio + litri
        // lancia IllegalArgumentException se litri < 0
        if (litri < 0)
            throw new IllegalArgumentException("Impossibile ricaricare di un numero negativo di litri");

        this.litriInSerbatoio += litri;
    }

    public void consumaLitri(double litri) throws CarburanteInsufficienteException {
        // MODIFIES: this
        // EFFECTS: esegue litriInSerbatoio_post = litriInSerbatoio - litri
        // lancia IllegalArgumentException se litri < 0
        // lancia CarburanteInsufficienteException se si richiede di consumare piu litri
        // di quanti ce ne siano nel serbatoio
        if (litri < 0)
            throw new IllegalArgumentException("Impossibile consumare un numero negativo di litri");

        if (this.litriInSerbatoio - litri < 0)
            throw new CarburanteInsufficienteException("Non hai carburante sufficiente");

        this.litriInSerbatoio -= litri;
    }

    public void setVelocitaCorrente(int velocitaCorrente)
            throws IllegalArgumentException, VelocitaMassimaSuperataException {
        // MODIFIES: this
        // EFFECTS: imposta this.velocitaCorrente = velocitaCorrente
        // lancia IllegalArgumentException se velocitaCorrente <= 0
        // lancia VelocitaMassimaSuperataException se la velocita che si vuole impostare
        // supera quella massima dell'auto
        if (velocitaCorrente <= 0)
            throw new IllegalArgumentException("Non puoi andare a velocita' negativa o nulla");

        if (velocitaCorrente >= this.velocitaMassima)
            throw new VelocitaMassimaSuperataException(
                    "L'auto non va cosi' veloce. velocita' limitata a " + this.velocitaMassima + "km/h");

        this.velocitaCorrente = velocitaCorrente;
    }

    public double viaggia(double distanza, int velocita)
            throws IllegalArgumentException, CarburanteInsufficienteException {
        // MODIFIES: this
        // EFFECTS: simula il viaggio dell'auto aggiornando il carburante rimanente e
        // restituendo il tempo necessario a percorrerlo (in ore)
        //  se velocita > this.velocitaMassima percorre il tragitto a velocita massima  
        // lancia IllegalArgumentException se distanza < 0 o velocità <= 0
        // lancia CarburanteNonSufficienteException se il carburante rimanente non e'
        // sufficiente a percorrere tutta la distanza indicata
        if (distanza < 0)
            throw new IllegalArgumentException("Non puoi percorrere distanza negativa");
        if (velocita <= 0)
            throw new IllegalArgumentException("Non puoi andare a velocita' negativa o nulla");

        double litriConsumati = distanza / this.consumoMedio;

        consumaLitri(litriConsumati);
        try {
            setVelocitaCorrente(velocita);
        } catch (VelocitaMassimaSuperataException e) {
            this.velocitaCorrente = this.velocitaMassima;
        }

        return distanza / this.velocitaCorrente; // tempo impiegato
    }

    public static void main(String[] args) {
        int capacitaSerbatoio = 0, velocitaMassima = 0;
        double consumoMedio = 0;

        try {
            capacitaSerbatoio = Integer.parseInt(args[0]);
            velocitaMassima = Integer.parseInt(args[1]);
            consumoMedio = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Parametri da riga di comando non numerici");
            System.exit(1);
        }

        Auto auto = new Auto(capacitaSerbatoio, velocitaMassima, consumoMedio);
        double distanzaTotale = 0;

        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci una tratta (<L. riforniti> <km da fare> <velocita'>)");
        while (s.hasNext()) {
            double litriRiforniti = s.nextDouble();
            double kmDaFare = s.nextDouble();
            int velocita = s.nextInt();

            try {
                auto.riempiSerbatoio(litriRiforniti);
                double tempoNecessarioInOre = auto.viaggia(kmDaFare, velocita);
                System.out.println("Tempo necessario: " + convertiInOreMinuti(tempoNecessarioInOre));

                distanzaTotale += kmDaFare;
            } catch (IllegalArgumentException | CarburanteInsufficienteException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Ti rimangono " + auto.getLitriInSerbatoio() + "L di carburante");
            System.out.println("Inserisci una tratta (<L. riforniti> <km da fare> <velocita'>)");

        }

        System.out.println("\nHai percorso " + distanzaTotale + " km totali");

    }

    private static String convertiInOreMinuti(double tempo) throws IllegalArgumentException {
        // EFFECTS: converte tempoNecessarioInOre, espresso in ore nel formato ore +
        // minuti (i.e.: 2.5h = 2 ore e 30 minuti)
        // lancia IllegalArgumentException se tempo < 0
        if (tempo < 0)
            throw new IllegalArgumentException("Orario negativo non valido");

        int ore = (int) tempo;
        int minuti = (int) ((tempo - (double) ore) * 60);
        return ore + " ore e " + minuti + " minuti";
    }

}