import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Percorso> percorsi = new ArrayList<>();

        Scanner s = new Scanner(System.in);    

        Percorso p = new Percorso();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            try {
            switch (in[0]) {
                case "-":
                    percorsi.add(new Percorso(p));
                    p = new Percorso();
                    break;
                case "TrattaAereo":
                    p.add(new TrattaAereo(in[1], in[2], Double.parseDouble(in[3]), Double.parseDouble(in[4]), Double.parseDouble(in[6]), Double.parseDouble(in[5])));
                    break;
                case "TrattaTreno":
                    p.add(new TrattaTreno(in[1], in[2], Double.parseDouble(in[3]), Double.parseDouble(in[4]), Double.parseDouble(in[5])));
                    break;
                case "TrattaBus":
                    p.add(new TrattaBus(in[1], in[2], Double.parseDouble(in[3]), Double.parseDouble(in[4]), Double.parseDouble(in[5])));
                    break;
                default:
                    System.out.println("Tipo di Tratta sconosciuto");
                    break;
            }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input non valido");
            } catch (NullPointerException | IllegalArgumentException | TrattaNonValidaException e) {
                System.out.println("Dati non validi: " + e.getMessage());
                System.exit(1);
            }
           
        }
         
        try {
                percorsi.add(new Percorso(p));
            } catch (NullPointerException | TrattaNonValidaException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        for (Percorso percorso : percorsi) {
            System.out.println("\n");
            System.out.println(percorso);
        }

    }

}
