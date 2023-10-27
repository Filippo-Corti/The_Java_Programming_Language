import java.util.ArrayList;
import java.util.Scanner;

public class Sheep implements Cloneable {

    String nome;
    ArrayList<String> cromosomi = new ArrayList<>();

    // constructors
    public Sheep(String nome) throws IllegalArgumentException {
        // EFFECTS: instanza una nuova Sheep
        // lancia IllegalArgumentExceptions se nome è nullo o vuoto
        if (nome == null || nome.equals(""))
            throw new IllegalArgumentException("Il nome della pecora non può essere nullo o vuoto");

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // methods
    public void aggiungiCromosoma(String c) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: aggiunge c alla lista di cromosomi
        // lancia IllegalArgumentException se c è nullo o vuoto
        if (c == null || c.equals(""))
            throw new IllegalArgumentException("I cromosomi non possono essere nulli o vuoti");
        this.cromosomi.add(c);
    }

    public String getCromosoma(int i) throws IndexOutOfBoundsException {
        // EFFECTS: ritorna l'i-esimo cromosoma di this
        // lancia IndexOutOfBoundsException se cromosomi.size() <= i || i < 0;
        return this.cromosomi.get(i);
    }

    public void sostituisciCromosoma(int i, String c) throws IndexOutOfBoundsException {
        // MODIFIES: this
        // EFFECTS: modifica l'i-esimo cromosoma di this a c
        // lancia IndexOutOfBoundsException se cromosomi.size() <= i || i < 0;
        this.cromosomi.set(i, c);
    }

    public int getNumeroCromosomi() {
        return this.cromosomi.size();
    }

    @Override
    protected Object clone() {
        Sheep s = null;
        try {
            s = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            s = new Sheep(this.nome);
        }

        s.cromosomi = (ArrayList<String>) this.cromosomi.clone();

        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sheep other = (Sheep) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cromosomi == null) {
            if (other.cromosomi != null)
                return false;
        } else {
            if (this.getNumeroCromosomi() != other.getNumeroCromosomi())
                return false;
            for (int i = 0; i < cromosomi.size(); i++) {
                if (!(other.getCromosoma(i).equals(this.getCromosoma(i))))
                    return false;
            }
        }
        return true;
    }

    public static void gestisciFatalException(Exception e) {
        // EFFECTS: stampa il messaggio di e e termina l'esecuzione
        System.out.println(e.getMessage());
        System.exit(1);
    }

    public static void controllaUguaglianzaSheepEStampa(Sheep s1, Sheep s2) {
        if (s1.equals(s2))
            System.out.println("Le due pecore sono uguali.");
        else
            System.out.println("Le due pecore sono diverse.");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Inserire un parametro da riga di comando");
            System.exit(1);
        }

        Sheep sheep1 = null;
        try {
            sheep1 = new Sheep(args[0]);
        } catch (IllegalArgumentException e) {
            gestisciFatalException(e);
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci i cromosomi, uno per riga (terminare con CTRL+D):");
        while (s.hasNextLine()) {
            try {
                sheep1.aggiungiCromosoma(s.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Sheep sheep2= (Sheep) sheep1.clone();
        System.out.println("Ho creato un clone di " + sheep1.getNome());

        controllaUguaglianzaSheepEStampa(sheep1, sheep2);

        Scanner s2 = new Scanner(System.in);

        System.out.println("Inserisci il cromosoma da modificare ed il nuovo codice:");
        try {
            sheep2.sostituisciCromosoma(s2.nextInt() - 1, s2.next());
        } catch (IndexOutOfBoundsException e) {
            gestisciFatalException(e);
        }

        System.out.println(sheep1.getCromosoma(2));
        System.out.println(sheep2.getCromosoma(2));

        controllaUguaglianzaSheepEStampa(sheep1, sheep2);
    }

}
