public class Mesi {

    public static void main(String[] args) {
        int giorni = 0;
        switch (args[0]) {
            case "gennaio", "marzo", "maggio", "luglio", "agosto", "ottobre", "dicembre":
                giorni = 31;
                break;
            case "febbraio":
                giorni = 28;
                break;
            case "aprile", "giugno", "settembre", "novembre":
                giorni = 30;
                break;
            default:
                System.out.println("Il nome del mese non e' corretto");
                System.exit(0);
                break;
        }
        System.out.println("Il numero di giorni di " + args[0] + " e' " + giorni);
    }

}
