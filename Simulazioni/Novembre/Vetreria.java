import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: classe che descrive un insieme di Contenitori di un laboratorio.
    // Iterabile su tali contenitori

    // attributes
    ArrayList<Contenitore> contenitori = new ArrayList<>();

    // methods
    public void add(Contenitore c) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: aggiunge c a this
        // lancia NullPointerException se c è nullo
        if (c == null)
            throw new NullPointerException("Contenitore nullo");

        contenitori.add((Contenitore) c.clone());

        assert repOk();
    }

    public void remove(Contenitore c) throws NullPointerException, NoSuchElementException {
        // MODIFIES: this
        // EFFECTS: rimuove c da this
        // lancia NullPointerException se c è nullo
        // lancia NoSuchElementException se c non è in this
        if (c == null)
            throw new NullPointerException("Contenitore nullo");

        if (!contenitori.remove(c))
            throw new NoSuchElementException("Contenitore non presente");

        assert repOk();
    }

    public Vetreria getOfType(String nomeLiquido) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: estrae da this tutti i contenitori con liquido nomeLiquido, li
        // rimuove e li restituisce come Vetreria
        // lancia NullPointerException se nomeLiquido è nullo
        Vetreria v = new Vetreria();

        for (Contenitore contenitore : this) {
            if (contenitore.getNomeLiquido().equals(nomeLiquido)) {
                v.add((Contenitore) contenitore);
            }
        }

        for (Contenitore c : v) {
            remove(c);
        }

        assert repOk();

        return v;
    }

    public void ottimizza() {
        // MODIFIES: this
        // EFFECTS: distribuisce i contenuti dei contenitori in modo da occupare meno
        // contenitori possibili, facendo attenzione a non mischiare liquidi
        this.sort();

        for (int i = contenitori.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                contenitori.get(j).travasa(contenitori.get(i));
            }
        }
    }

    @Override
    public Iterator<Contenitore> iterator() {
        // EFFECTS: restituisce un iteratore ai contenitori presenti
        return new Iterator<Contenitore>() {

            Iterator<Contenitore> i = contenitori.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Contenitore next() {
                return i.next();
            }

        };
    }

    public void sort() {
        // MODIFIES: this
        // EFFECTS: ordina in ordine DECRESCENTE i contenitori, in base al loro volume
        contenitori.sort(null);
    }

    public boolean repOk() {
        if (contenitori == null)
            return false;

        for (Contenitore contenitore : contenitori) {
            if (contenitore == null)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String res = "Vetrina con:\n";
        for (Contenitore contenitore : contenitori) {
            res += " " + contenitore + "\n";
        }
        return res;
    }

    public HashSet<String> getTipiContenuti() {
        HashSet<String> tipi = new HashSet<>();
        //EFFECTS: ritorna un Set contenente tutti i tipi di liquidi che i contenitori di this contengono
        for (Contenitore contenitore : contenitori) {
            tipi.add(contenitore.getNomeLiquido());
        }

        return tipi;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Vetreria v = new Vetreria();

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            try {
                switch (in[2]) {
                    case "Sfera":
                        v.add(new Sfera(in[0], Double.parseDouble(in[1]), Double.parseDouble(in[3])));
                        break;
                    case "Cuboide":
                        v.add(new Cuboide(in[0], Double.parseDouble(in[1]), Double.parseDouble(in[3]),
                                Double.parseDouble(in[4]), Double.parseDouble(in[5])));
                        break;
                    case "Cilindro":
                        v.add(new Cilindro(in[0], Double.parseDouble(in[1]), Double.parseDouble(in[4]),
                                Double.parseDouble(in[3])));
                        break;
                    default:
                        System.out.println("Tipo Contenitore non riconosciuto");
                        break;
                }
            } catch (NullPointerException | IllegalArgumentException | ExceededCapacityException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        System.out.println(v);

        for (String tipo : v.getTipiContenuti()) {
            Vetreria vetreriaStessoTipo = v.getOfType(tipo);
            vetreriaStessoTipo.ottimizza();
            System.out.println();
            System.out.println(vetreriaStessoTipo);
            System.out.println();
        }
    

    }

}
