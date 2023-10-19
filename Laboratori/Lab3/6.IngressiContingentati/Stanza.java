import java.util.ArrayList;
import java.util.Scanner;

public class Stanza {
    // OVERVIEW: una classe che descrive una Stanza avente una capienza massima e in
    // cui entrano ed escono persone

    final int capienza;
    int persone;

    // constructors
    public Stanza(int capienza) throws IllegalArgumentException {
        // EFFECTS: istanzia una nuova Stanza
        // lancia IllegalArgumentException se capienza < 0

        if (capienza < 0)
            throw new IllegalArgumentException("Non e' possibile avere una stanza con capienza negativa");

        this.capienza = capienza;
        this.persone = 0;
    }

    // methods
    public int getPersone() {
        return persone;
    }

    public void personaEsce() throws StanzaGiaVuotaException {
        // MODIFIES: this
        // EFFECTS: persone_post = persone - 1;
        // lancia StanzaGiaVuotaException se persone = 0
        if (this.persone == 0) {
            throw new StanzaGiaVuotaException("Nessuno nella stanza");
        }

        this.persone--;
    }

    public void personaEntra() throws StanzaGiaPienaException {
        // MODIFIES: this
        // EFFECTS: persone_post = persone + 1;
        // lancia StanzaGiaPienaException se persone = capienza
        if (this.persone == this.capienza) {
            throw new StanzaGiaPienaException("Capienza massima raggiunta nella stanza");
        }

        this.persone++;
    }

    public static void aggiornaStanze(ArrayList<Stanza> stanze, int idStanza, char operazione)
            throws IllegalArgumentException, StanzaNonEsisteException, StanzaGiaPienaException,
            StanzaGiaVuotaException {
        // MODIFIES: stanze
        // EFFECTS: aggiorna la lista stanze cambiando il numero di persone nella stanza
        // numero idStanza. operazione può essere + o -.
        // lancia IllegalArgumentException se stanze = null o se operazione non e' un
        // carattere valido
        // lancia StanzaNonEsisteException se idStanza > stanze.size()
        // lancia StanzaGiaPienaException se si vuole aggiungere una persona a una
        // stanza già piena
        // lancia StanzaGiaVuotaException se si vuole togliere una persona da una stanza
        // vuota
        if (stanze == null)
            throw new IllegalArgumentException("Stanze non puo' essere un valore nullo");

        if (idStanza > stanze.size())
            throw new StanzaNonEsisteException("Stanza " + idStanza + " non esiste");

        switch (operazione) {
            case '+':
                stanze.get(idStanza - 1).personaEntra();
                break;
            case '-':
                stanze.get(idStanza - 1).personaEsce();
                break;
            default:
                throw new IllegalArgumentException("Operazione " + operazione + " non riconosciuta");
        }
    }

    public static int contaTotalePersone(ArrayList<Stanza> stanze) {
        // EFFECTS: ritorna il numero totale di persone contenuto nelle stanze
        // lancia IllegalArgumentException se stanze = null

        if (stanze == null)
            throw new IllegalArgumentException("Stanze non puo' essere un valore nullo");

        int tot = 0;
        for (Stanza stanza : stanze) {
            tot += stanza.getPersone();
        }
        return tot;
    }

    public static void main(String[] args) {

        ArrayList<Stanza> stanze = new ArrayList<>();

        for (String string : args) {
            try {
                int capienza = Integer.parseInt(string);
                stanze.add(new Stanza(capienza));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        System.out.println("Create " + stanze.size() + " stanze");

        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            String[] in = s.nextLine().split(" ");
            try {
                aggiornaStanze(stanze, Integer.parseInt(in[0]), in[1].charAt(0));
            } catch (IllegalArgumentException| StanzaNonEsisteException e) {
                System.out.println(e.getMessage());
            } catch (StanzaGiaPienaException | StanzaGiaVuotaException e) {
                System.out.println(e.getMessage() + " " + Integer.parseInt(in[0]));
            }
        }

        System.out.println("\nNumero totale di persone presenti: " + contaTotalePersone(stanze));

    }

}
