import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RicevimentoStudenti {
    // OVERVIEW: a static class to book new appointments

    private static int APPOINTMENTS_PER_DAY = 6;
    private static int STARTING_HOUR = 13;

    public static void prenota(String[] agenda, int ora, String nome)
            throws NullPointerException, ArrayStoreException, IllegalArgumentException {
        // MODIFIES: agenda
        // EFFECTS: books a new appointment in agenda at time ora, for the student nome.
        if (agenda == null || nome == null)
            throw new NullPointerException("Argument is null");

        if (agenda.length != APPOINTMENTS_PER_DAY)
            throw new IllegalArgumentException("Wrong size for agenda");

        if (ora < STARTING_HOUR || ora > STARTING_HOUR + APPOINTMENTS_PER_DAY - 1)
            throw new IllegalArgumentException("Non e' un'ora corretta");

        if (nome == "")
            throw new IllegalArgumentException("Il nome e' vuoto");

        if ((agenda[ora - STARTING_HOUR] != null))
            throw new ArrayStoreException(
                    "L'orario specificato e' gia' allocato per un altro appuntamento");

        if (contains(agenda, nome))
            throw new ArrayStoreException("Lo studente ha gia' prenotato un appuntamento");

        agenda[ora - STARTING_HOUR] = nome;
    }

    private static boolean contains(String[] haystack, String needle) throws NullPointerException {
        // EFFECTS: returns true if needle is in the haystack, false otherwise
        // throws NullPointerException if either of the arguments are null
        if (haystack == null || needle == null)
            throw new NullPointerException();

        for (String string : haystack) {
            if (string != null && string.equals(needle))
                return true;
        }
        return false;
    }

    public static int[] orariDisponibili(String[] agenda) throws NullPointerException, IllegalArgumentException {
        // EFFECTS: returns the hours where agenda is not booked yet.
        if (agenda == null)
            throw new NullPointerException("Argument is null");

        if (agenda.length != APPOINTMENTS_PER_DAY)
            throw new IllegalArgumentException("Agenda doesn't have exactly 6 elements");

        List<Integer> notBooked = new ArrayList<>();
        for (int j = 0; j < agenda.length; j++) {
            if (agenda[j] == null) {
                notBooked.add(j + STARTING_HOUR);
            }
        }
        return notBooked.stream().mapToInt(x -> x).toArray();

    }

    public static void stampaAgenda(String[] agenda) throws NullPointerException, IllegalArgumentException {
        // EFFECTS: prints agenda in the format "Alle {13-18} e' fissato l'appuntamento
        // di {nome}"
        if (agenda == null)
            throw new NullPointerException("Argument is null");

        if (agenda.length != APPOINTMENTS_PER_DAY)
            throw new IllegalArgumentException("Agenda doesn't have exactly 6 elements");

        for (int i = 0; i < agenda.length; i++) {
            System.out.println("Alle " + (i + STARTING_HOUR) + " e' fissato l'appuntamento di " + agenda[i]);
        }
    }

    public static void stampaOrariDisponibili(int[] orari) throws NullPointerException {
        // EFFECTS: prints orari
        if (orari == null)
            throw new NullPointerException("Argument is null");

        System.out.print("Puoi prenotare un appuntamento alle ");
        for (int i = 0; i < orari.length - 1; i++) {
            System.out.print(orari[i] + ", ");
        }
        System.out.println(orari[orari.length - 1]);
    }

    private static boolean controllaEStampaOrari(String[] agenda) {
        // EFFECTS: stampa gli orari disponibili per appuntamenti in agenda.
        // Returns false se tutti gli orari sono occupati. True altrimenti
        int[] orariDisponibili = orariDisponibili(agenda);
        if (orariDisponibili.length == 0) {
            return false;
        }
        stampaOrariDisponibili(orariDisponibili);
        return true;
    }

    private static String chiediNome() {
        // EFFECTS: chiede un nome all'utente e lo ritorna
        Scanner s = new Scanner(System.in);
        System.out.println("Qual e' il tuo nome?");
        String nome = "";
        try {
            nome = s.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Problema nella lettura della stringa");
            System.exit(1);
        }
        return nome;
    }

    private static int chiediOra() {
        // EFFECTS: chiede un orario all'utente e lo ritorna
        Scanner s = new Scanner(System.in);
        System.out.println("A che ora vorresti l'appuntamento?");
        int ora = 0;
        try {
            ora = s.nextInt();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Problema nella lettura dell'intero");
            System.exit(1);
        }
        return ora;
    }

    public static void main(String[] args) {
        String[] agenda = new String[6];
        String nome = "";
        int ora = 0;
        boolean nomeCheck, oraCheck;
        while (true) {
            nomeCheck = false;
            oraCheck = false;

            if (!controllaEStampaOrari(agenda))
                break; // Non c'è più spazio per altri appuntamenti

            while (!(nomeCheck && oraCheck)) {
                if (!nomeCheck) {
                    nome = chiediNome();
                }
                if (!oraCheck) {
                    ora = chiediOra();
                }

                /* Verifica Validità Dati */
                try {
                    nomeCheck = true;
                    oraCheck = true;
                    prenota(agenda, ora, nome);
                } catch (NullPointerException e) {
                    /* L'agenda è null => termina il programma */
                    System.out.println(e.getMessage());
                    System.exit(1);
                } catch (ArrayStoreException e) {
                    /*
                     * L'orario è già prenotato (richiedi ora) o lo studente ha giù un appuntamento
                     * (richiedi nome e ora)
                     */
                    if (e.getMessage().equals("Lo studente ha gia' prenotato un appuntamento")) {
                        nomeCheck = false;
                        oraCheck = false;
                    } else
                        oraCheck = false;
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    /*
                     * L'agenda non ha 6 elementi (termina) o l'orario non è valido (richiedi ora) o
                     * il nome dello studente è vuoto (richiedi nome e ora)
                     */
                    if (e.getMessage().equals("Wrong size for agenda"))
                        System.exit(1);
                    else if (e.getMessage().equals("Non e' un'ora corretta"))
                        oraCheck = false;
                    else {
                        nomeCheck = false;
                        oraCheck = false;
                    }
                    System.out.println(e.getMessage());
                }

            }
            // s.nextLine(); // Altrimenti saltava degli input
        }
        System.out.println("Fissati tutti gli appuntamenti");

        stampaAgenda(agenda);
    }

}
