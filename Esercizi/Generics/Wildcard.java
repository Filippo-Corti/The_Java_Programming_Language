import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.DoubleToLongFunction;

public class Wildcard {

    public static void main(String[] args) {
        /*ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ArrayList<Double> list2 = new ArrayList<Double>(Arrays.asList(3.5, 6.7, 3.4, 7.0));
        stampa(list);
        System.out.println(addAll(list));
        stampa(list2);
        System.out.println(addAll(list2));*/

        //riempiRandom(new ArrayList<Integer>());
        ArrayList<Integer> lista = new ArrayList<Integer>();
        //lista.add(38.7);
        riempiRandom(lista);
        System.out.println(lista); //Ha dentro un Object????
        System.out.println(lista.get(0).intValue()); //PPROBLEMAAAAAAAAAAA

        ArrayList<Double> l = new ArrayList<>();
        riempiRandom2(l);
    }

    //@SuppressWarnings("unchecked")
    public static <T extends Number> void riempiRandom(ArrayList<T> list) {
        list.add((T) Double.valueOf(38.5));
    }

    public static void riempiRandom2(ArrayList<? super Integer> list) {
        list.add(38);
    }

    public static void stampa(ArrayList<?> wildList) {
        for (Object object : wildList) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public static void aggiungi(ArrayList<? extends Number> wildList, Number n) {
        //wildList.add(n); //Non si può fare
    }

    public static <T extends Animale> void func(ArrayList<T> list, T object) {
        list.add(object);
    }

    public static double addAll(ArrayList<? extends Number> list) {
        double sum = 0;
        //list.add(new Number());
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    
}
