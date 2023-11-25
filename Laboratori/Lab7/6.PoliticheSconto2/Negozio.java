import java.util.ArrayList;
import java.util.Scanner;

public class Negozio {

    /* Esempio di Input: */
    /*
10 10 SQ-2-10
5 20 SU-3
18 1 BF
3 40 SA-100
SC[10 10 SA-4, 10 10 BF, 10 10 SU-3, 10 10 SQ-2-10]
     */

    public static PoliticaSconto parsePoliticaSconto(int numeroArticoli, double prezzoArticolo, String s) {
        String[] campiSconto = s.split("-");
        switch (s.substring(0, 2)) {
            case "SQ":
                return new ScontoQuantita(
                        numeroArticoli,
                        prezzoArticolo,
                        Integer.parseInt(campiSconto[1]),
                        Double.parseDouble(campiSconto[2]));
            case "SU":
                return new UnoOgniNGratis(
                        numeroArticoli,
                        prezzoArticolo,
                        Integer.parseInt(campiSconto[1]));
            case "BF":
                return new BlackFriday(numeroArticoli, prezzoArticolo);
            case "SA":
                return new ScontoAmici(
                        numeroArticoli,
                        prezzoArticolo,
                        Double.parseDouble(campiSconto[1]));
            case "SC":
                String[] sottopolitiche = s.substring(3, s.length() - 1).split(", ");
                ArrayList<PoliticaSconto> politicheCombinate = new ArrayList<>();
                for (String string : sottopolitiche) {
                    String[] in = string.split(" ");
                    numeroArticoli = Integer.parseInt(in[0]);
                    prezzoArticolo = Double.parseDouble(in[1]);
                    politicheCombinate.add(parsePoliticaSconto(numeroArticoli, prezzoArticolo, in[2]));
                }
                return new ScontoCombinato(politicheCombinate);
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<PoliticaSconto> politicheSconto = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            String in = s.nextLine();
            if (in.substring(0, 2).equals("SC")) {
                politicheSconto.add(parsePoliticaSconto(0, 0, in));
            } else {
                String[] splitIn = in.split(" ");
                int numeroArticoli = Integer.parseInt(splitIn[0]);
                double prezzoArticolo = Double.parseDouble(splitIn[1]);
                politicheSconto.add(parsePoliticaSconto(numeroArticoli, prezzoArticolo, splitIn[2]));
            }
        }

        for (PoliticaSconto politicaSconto : politicheSconto) {
            System.out.println("\n\nPolitica: " + politicaSconto + " - Sconto: " + politicaSconto.calcolaSconto());
        }

    }

}
