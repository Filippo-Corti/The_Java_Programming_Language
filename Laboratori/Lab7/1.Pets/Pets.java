import java.util.ArrayList;
import java.util.Scanner;

public class Pets {

    public static boolean containsByNome(ArrayList<Persona> p, String nome) {
        if (nome == null)
            return false;
        for (Persona persona : p) {
            if (persona.getNome().equals(nome)) 
                return true;
        }
        return false;
    }

    public static Persona getByName(ArrayList<Persona> p, String nome) {
        if (nome == null)
            return null;
        for (Persona persona : p) {
             if (persona.getNome().equals(nome)) 
                return persona;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArrayList<Persona> persone = new ArrayList<>();

        System.out.println("Inserisci righe nel formato `nomePersona nomeAnimale tipoAnimale` (CTRL+D per terminare)");

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            if (!containsByNome(persone, in[0])) 
                persone.add(new Persona(in[0]));
            switch (in[2]) {
                case "Cane":
                    getByName(persone, in[0]).insert(new Cane(in[1]));
                    break;
                case "Gatto":
                    getByName(persone, in[0]).insert(new Gatto(in[1]));
                    break;
                case "Cavia":
                    getByName(persone, in[0]).insert(new Cavia(in[1]));
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
