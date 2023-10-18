import java.util.ArrayList;
import java.util.Scanner;

public class Armageddon {
    // OVERVIEW: classe statica che testa la classe Asteroide

    public static String toStringAsteroidePericoloso(Asteroide a) {
        // EFFECTS: ritorna una stringa corrispondente alla descrizione di a, segnalando
        // che e' pericoloso
        return a.toString().replace("Asteroide", "Asteroide pericoloso");
    }

    public static void main(String[] args) {
        double soglia = 0;
        try {
            soglia = Double.parseDouble(args[0]);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Parametro non valido");
        }

        Scanner s = new Scanner(System.in);
        ArrayList<Asteroide> asteroidi = new ArrayList<>();
        while (s.hasNext()) {
            double massa = s.nextDouble();
            double distanza = s.nextDouble();
            asteroidi.add(new Asteroide(massa, distanza));
        }

        for (Asteroide a : asteroidi) {
            if (a.getForzaGravitazionale() > soglia) {
                System.out.println(toStringAsteroidePericoloso(a));
            }
        }
    }

}
