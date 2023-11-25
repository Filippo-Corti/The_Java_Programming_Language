import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;

public class Main {

    static Function<Double, Function<Double, Double>> pow2 = (base) -> (exp) -> Math.pow(base, exp);
    
    static BiFunction<Double, Double, Double> pow = (base, exp) -> Math.pow(base, exp);

    static Function<Double, Double> square = (x) -> pow.apply(x, 2.);

    static BiFunction<Double, Double, Double> subtract = (x, y) -> x - y;

    static Function<ArrayList<Integer>, Integer> sum = (list) -> list.stream().reduce(0, (tot, el) -> tot + el);

    static Function<Double, Integer> doubleListToIntList = (x) -> x.intValue();

    static Function<ArrayList<Integer>, Integer> max = (list) -> list.stream().reduce(list.get(0), (max, el) -> el > max ? el : max);

    public static void main(String[] args) {

        // System.out.println(pow.apply(Math.E, 2.));
        // System.out.println(pow2.apply(Math.E).apply(2.));

        ArrayList<Double> list = new ArrayList<>(Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8.));
        System.out.println(list);

        ArrayList<Double> newList = square(list);
        newList.sort((o1, o2) -> Double.compare(o2, o1));
        System.out.println(newList);

        ArrayList<Integer> intList = new ArrayList<Integer>(newList.stream().map(doubleListToIntList).toList());

        System.out.println(sum.apply(intList));

        System.out.println(max.apply(intList));

    }

    public static ArrayList<Double> square(ArrayList<Double> list) {
        return new ArrayList<Double>(list.stream().map(square).toList());
    }

}
