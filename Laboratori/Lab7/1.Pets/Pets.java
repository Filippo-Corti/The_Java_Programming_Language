import java.util.ArrayList;
import java.util.Scanner;

public class Pets {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArrayList<Persona> persone = new ArrayList<>();

        System.out.println("Inserisci righe nel formato `nomePersona nomeAnimale tipoAnimale` (CTRL+D per terminare)");

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            if (!persone.contains(new Persona(in[0]))) 
                persone.add(new Persona(in[0]));
            switch (in[2]) {
                case "Cane":
                    persone.get(persone.indexOf(new Persona(in[0]))).insert(new Cane(in[1]));
                    break;
                case "Gatto":
                    persone.get(persone.indexOf(new Persona(in[0]))).insert(new Gatto(in[1]));
                    break;
                case "Cavia":
                    persone.get(persone.indexOf(new Persona(in[0]))).insert(new Cavia(in[1]));
                    break;
                default:
                    System.out.println("Animale non riconosciuto");
                    break;
            }
        }

        System.out.println();

        for (Persona persona : persone) {
            persona.dirigiCoro();
            System.out.println();
        }

    }

}
