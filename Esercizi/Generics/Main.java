import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        //ArrayList<Animale> animali = new ArrayList<>();
        //ArrayList<Mammifero> mammiferi = new ArrayList<>();

        // mammiferi = animali; //Questa non va
        // animali = mammiferi; //Questa nemmeno

        ArrayList<Object> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Object[] array = new Object[]{7, 8, 9, "Ciao", new Animale("delfino")};

        fromArrayToCollection3(array, list);
        System.out.println(list);
    }

    public static <T> void stampaArrayListGenerica(ArrayList<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    public static <T extends Number> void stampaArrayListNumerici(ArrayList<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    public static void fromArrayToCollection(Object[] a, Collection<Object> c) { // Questo funziona solo sulle Collection di Object (no subtyping con Collection<Type>)
        for (Object o : a) {
            c.add(o);
        }
    }

    public static void fromArrayToCollection2(Object[] a, Collection<?> c) { 
        for (Object o : a) {
            c.add(o); //Questa sembra funzoinare ma ha problemi qui, poichè Object potrebbe contenere elementi non Object e che non potrebbero venire aggiunti a collection
        }
    }

    public static <T> void fromArrayToCollection3(T[] a, Collection<T> c) { 
        for (T o : a) {
            c.add(o); //Questa effettivamente funziona :)
        }
    }

}
