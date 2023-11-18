public class Main {
    // OVERVIEW: classe di testing della Gerarchia di Tipi PoliticaSconto

    /*
     * 
     * NOTA: secondo la consegna ScontoCombinato deriva dalla stessa classe di
     * PoliticaSconto ma ne contiene anche altre due. Questo significa che
     * ScontoCombinato contiene i propri numeroArticoli e prezzoArticolo, ma anche
     * le sue due PoliticaSconto avranno i propri.
     * 
     * La soluzione (sebbene non perfetta) che ho pensato è che ScontoCombinato sia
     * di fatto formato solo da due PoliticaSconto, che per essere combinate
     * dovranno necessariamente essere applicate su uguale numeroArticoli di uguale
     * prezzoArticolo.
     * Poiché di fatto ScontoCombinato avrebbe in comune con le PoliticaSconto
     * l'esistenza di un metodo calcolaSconto(), PoliticaSconto prende il nome di
     * ScontoSemplice (due ScontoSemplice fanno uno ScontoCombinato) e una nuova
     * interfaccia PoliticaSconto accomuna ScontoSemplice e ScontoCombinato.
     * 
     */

    public static void main(String[] args) {
        ScontoSemplice politica1 = new ScontoQuantita(
                10, 10, 5, 20);
        ScontoSemplice politica2 = new UnoOgniNGratis(
                11, 10, 3);
        ScontoSemplice politica3 = new UnoOgniNGratis(
                politica1.numeroArticoli, politica1.prezzoArticolo, 3);

        System.out.println("Politica1: " + politica1.toString());
        System.out.println("Sconto: " + politica1.calcolaSconto());

        System.out.println("Politica2: " + politica2.toString());
        System.out.println("Sconto: " + politica2.calcolaSconto());

        System.out.println("Politica3: " + politica3.toString());
        System.out.println("Sconto: " + politica3.calcolaSconto());

        ScontoCombinato scontoCombinato = null;

        // Caso di Politiche non combinabili
        try {
            scontoCombinato = new ScontoCombinato(politica1, politica2);
            System.out.println("Politiche 1 e 2 Combinabili! Sconto: " + scontoCombinato.calcolaSconto());
        } catch (IllegalArgumentException e) {
            System.out.println("Politiche 1 e 2 Non Combinabili!");
        }

        // Caso di Politiche combinabili
        try {
            scontoCombinato = new ScontoCombinato(politica1, politica3);
            System.out.println("Politiche 1 e 3 Combinabili! Sconto Combinato: " + scontoCombinato.calcolaSconto());
        } catch (IllegalArgumentException e) {
            System.out.println("Politiche 1 e 3 Non Combinabili!");
        }

    }

}
