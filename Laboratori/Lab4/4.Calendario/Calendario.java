import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Calendario {
    // OVERVIEW: classe mutabile che modella un Calendario che utilizza la classe
    // immutabile Evento

    // attributes
    ArrayList<Evento> eventi;

    // constructors
    public Calendario() {
        // MODIFIES: this
        // EFFECTS: istanza un nuovo oggetto di tipo Calendario, senza eventi
        eventi = new ArrayList<>();

        assert repOk();
    }

    // methods

    public boolean hasEvento(Evento e)
            throws NullPointerException {
        // EFFECTS: ritorna true se this contiene e. False altrimenti
        // lancia NullPointerException se e è nullo
        if (e == null)
            throw new NullPointerException("Parametro nullo non valido");

        return eventi.contains(e);
    }

    public boolean hasEvento(Date data, String nome)
            throws NullPointerException, IllegalArgumentException {
        // EFFECTS: ritorna true se this contiene un evento avente data e nome pari ai
        // parametri passati. False altrimenti
        // lancia NullPointerException se data o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto
        if (data == null || nome == null)
            throw new NullPointerException("Parametro nullo non valido");

        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto non valido");

        return eventi.contains(new Evento(data, nome));
    }

    public void addEvento(Evento e) throws NullPointerException, EventoDuplicatoException {
        // MODIFIES: this
        // EFFECTS: aggiunge e agli eventi in this
        // lancia NullPointerException se e è nullo
        // lancia EventoDuplicatoException se e e è già presente in this. In tal caso
        // l'evento non viene aggiunto
        if (e == null)
            throw new NullPointerException("Evento non può essere nullo");

        if (eventi.contains(e))
            throw new EventoDuplicatoException("Evento già presente in calendario");

        eventi.add(e);

        assert repOk();
    }

    public void addEvento(Date data, String nome)
            throws NullPointerException, IllegalArgumentException, EventoDuplicatoException {
        // MODIFIES: this
        // EFFECTS: aggiunge a this un evento avente come parametri i parametri passati
        // lancia NullPointerException se data o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto
        // lancia EventoDuplicatoException se è già presente in this un evento uguale
        if (data == null || nome == null)
            throw new NullPointerException("Parametro nullo non valido");

        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto non valido");

        Evento e = new Evento(data, nome);

        if (eventi.contains(e))
            throw new EventoDuplicatoException("Evento già presente in calendario");

        eventi.add(e);

        assert repOk();
    }

    public void deleteEvento(Evento e) throws NullPointerException, EventoNonPresenteException {
        // MODIFIES: this
        // EFFECTS: rimuove e dagli eventi in this
        // lancia NullPointerException se e è nullo
        // lancia EventoNonPresenteException se e non si trova in this
        if (e == null)
            throw new NullPointerException("Evento non può essere nullo");

        if (!eventi.remove(e))
            throw new EventoNonPresenteException("Evento non presente in calendario");

        assert repOk();
    }

    public void deleteEvento(Date data, String nome) throws NullPointerException, EventoNonPresenteException {
        // MODIFIES: this
        // EFFECTS: rimuove dagli eventi in this un evento avente le caratteristiche
        // specificate
        // lancia NullPointerException se data o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto
        // lancia EventoNonPresenteException se e non si trova in this
        if (data == null || nome == null)
            throw new NullPointerException("Parametro nullo non valido");

        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto non valido");

        Evento e = new Evento(data, nome);

        if (!eventi.remove(e))
            throw new EventoNonPresenteException("Evento non presente in calendario");

        assert repOk();
    }

    public void copiaEvento(Evento e, int n) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inserisce in this un nuovo evento uguale ad e ma a n giorni di
        // distanza da esso. L'evento da copiare non deve necessariamente essere già
        // presente in this
        // lancia NullPointerException se e è nullo
        // lancia IllegalArgumentException se n <= 0
        if (e == null)
            throw new NullPointerException("Evento non può essere nullo");

        eventi.add(e.copiaEvento(n));

        assert repOk();
    }

    public boolean repOk() {
        if (eventi == null)
            return false;

        for (int i = 0; i < eventi.size(); i++) {
            for (int j = i + 1; j < eventi.size(); j++) {
                if (eventi.get(i).equals(eventi.get(j)))
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String res = "Calendario: (\n";
        for (Evento evento : eventi) {
            res += "\t\t" + evento.toString() + "\n";
        }
        return res + ")";
    }

    public static void main(String[] args) {
        Calendario calendario = new Calendario();

        Scanner s = new Scanner(System.in);

        System.out.println("Inserisci +/-/* NomeEvento gg/mm/aaaa [offset] (termina con CTRL+D)");

        while (s.hasNext()) {
            String[] in = s.nextLine().split(" ");
            String[] campiData = in[2].split("/");

            if (in.length < 3 || in.length > 4 || in.length == 4 && !in[0].equals("*") || campiData.length != 3) {
                System.out.println("Sintassi di input non valida");
            } else {
                String nome = in[1];
                Date data = new Date(Integer.parseInt(campiData[2]) - 1900,
                        Integer.parseInt(campiData[1]),
                        Integer.parseInt(campiData[0]));

                try {
                    switch (in[0]) {
                        case "+":
                            calendario.addEvento(data, nome);
                            break;
                        case "-":
                            calendario.deleteEvento(data, nome);
                            break;
                        case "*":
                            calendario.copiaEvento(new Evento(data, nome), Integer.parseInt(in[3]));
                            break;
                        default:
                            System.out.println("Sintassi di input non valida");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Sintassi di input non valida");
                } catch (EventoDuplicatoException | EventoNonPresenteException | NullPointerException
                        | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            }

        }

        System.out.println(calendario);

    }

}
