import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Dottore> leggiDottori() {
        Scanner s = new Scanner(System.in);
        ArrayList<Dottore> dottori = new ArrayList<>();
        System.out.println("Inserisci medici nel formato `nome specializzazione parcella` (termina con CTRL+D)");
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            try {
                dottori.add(new Dottore(in[0], in[1], Double.parseDouble(in[2])));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input nel formato non corretto");
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return dottori;
    }

    public static ArrayList<Paziente> leggiPazienti() {
        Scanner s = new Scanner(System.in);
        ArrayList<Paziente> pazienti = new ArrayList<>();
        System.out.println("Inserisci i pazienti nel formato `nome codice` (termina con CTRL+D)");
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            try {
                pazienti.add(new Paziente(in[0], in[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Input nel formato non corretto");
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return pazienti;
    }

    public static ArrayList<Visita> leggiVisite() {
        Scanner s = new Scanner(System.in);
        ArrayList<Visita> visite = new ArrayList<>();
        System.out.println("Inserisci i pazienti nel formato `nome codice` (termina con CTRL+D)");
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            try {
                visite.add(new Visita(in[0], Integer.parseInt(in[1])));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input nel formato non corretto");
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return visite;
    }

    private static double calcolaGuadagni(ArrayList<Visita> visite, Dottore dottore) {
        //EFFECTS: ritorna il guadagno del dottore dottore in base alle sue visite tra visite
        double guadagno = 0;
        for (Visita visita : visite) {
            if (visita.getNomeDottore().equals(dottore.getNome()))
            guadagno += dottore.getParcella();
        }
        return guadagno;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Dottore> dottori = leggiDottori();
        ArrayList<Paziente> pazienti = leggiPazienti();
        ArrayList<Visita> visite = leggiVisite();

        System.out.println("I guadagni del mese sono:");
        for (Dottore dottore : dottori) {
            double guadagnoMensile = calcolaGuadagni(visite, dottore);
            if (guadagnoMensile > 0)
                System.out.println(dottore.getNome() + " " + guadagnoMensile);
        }

    }


}
