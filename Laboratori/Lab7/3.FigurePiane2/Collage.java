import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Collage implements Iterable<Figura>{
    // OVERVIEW: classe che descrive una lista di Figure Geometriche immutabili.
    //  Iterabile sulle sue Figure (non che fosse necessario ma viene comodo nel main)

    // attributes
    ArrayList<Figura> figure = new ArrayList<>();

    // methods
    public void add(Figura f) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: inserisce f in this.
        // lancia NullPointerException se f è nulla
        if (f == null)
            throw new NullPointerException("Figura nulla");

        figure.add(f);

        assert repOk();
    }

    public void remove(Figura f) throws NullPointerException, NoSuchElementException {
        // MODIFIES: this
        // EFFECTS: rimuove f da this
        // lancia NullPointerException se f è nulla
        // lancia NoSuchElementException se f non è in this
        if (f == null)
            throw new NullPointerException("Figura nulla");

        figure.remove(f);

        assert repOk();
    }

    public void ordinaPerPerimetro() {
        // MODIFIES: this
        // EFFECTS: ordina this.figure secondo il loro perimetro
        figure.sort(null);

        assert repOk();
    }

    public void ordinaPerArea() {
        // MODIFIES: this
        // EFFECTS: ordina this.figure secondo il loro perimetro
        figure.sort(new Comparator<>() {
            @Override
            public int compare(Figura o1, Figura o2) {
                return Double.compare(o2.area(), o1.area()); //Ordine decrescente
            }
        });

        assert repOk();
    }

    @Override
    public String toString() {
        String res = "";
        for (Figura figura : figure) {
            res += figura + "\n";
        }
        return res;
    }

    public boolean repOk() {
        if (figure == null)
            return false;

        for (Figura figura : figure) {
            if (figura == null)
                return false;
        }

        return true;
    }

    @Override
    public Iterator<Figura> iterator() {
        return new Iterator<>() {

            Iterator<Figura> i = figure.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Figura next() {
                return i.next();
            }

        };
    }

    public static void main(String[] args) {
        Collage collage = new Collage();
        Scanner s = new Scanner(System.in);

        System.out.println("Inserisci delle figure (Termina con CTRL+D)");
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            try {
                switch (in[0]) {
                    case "Triangolo":
                        collage.add(
                                new Triangolo(
                                        Double.parseDouble(in[1]),
                                        Double.parseDouble(in[2]),
                                        Double.parseDouble(in[3])));
                        break;
                    case "Rettangolo":
                        collage.add(
                                new Rettangolo(
                                        Double.parseDouble(in[1]),
                                        Double.parseDouble(in[2])));
                        break;
                    case "Quadrato":
                        collage.add(
                                new Quadrato(
                                        Double.parseDouble(in[1])));
                        break;
                    case "Cerchio":
                        collage.add(
                                new Cerchio(
                                        Double.parseDouble(in[1])));
                        break;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input non valido");
            } catch (IllegalArgumentException | NullPointerException | ImpossibleTriangleException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        double areaTotale = 0;
        for (Figura figura : collage) {
            areaTotale += figura.area();
        }
        
        System.out.println("\nArea totale: " + areaTotale);

        System.out.println("\nOrdinati per area:");
        collage.ordinaPerArea();
        System.out.println(collage);

        System.out.println("\nOrdinati per perimetro:");
        collage.ordinaPerPerimetro();
        System.out.println(collage);
    }


}
