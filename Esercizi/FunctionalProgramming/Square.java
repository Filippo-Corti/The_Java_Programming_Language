import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Square {

    static BiFunction<Double, Double, Double> pow = (base, exp) -> Math.pow(base, exp);

    static Function<Double, Double> square = (x) -> pow.apply(x, 2.);

    public static void main(String[] args) {
        // ArrayList<Double> numbers = new ArrayList<>(Arrays.asList(1., 2., 3., 4., 5.,
        // 6., 7., 8.));

        // ArrayList<Double> squared = new
        // ArrayList<>(numbers.stream().map(square).toList());

        // System.out.println(squared); // [1.0, 4.0, 9.0, 16.0, 25.0, 36.0, 49.0, 64.0]

        ArrayList<Double> numbers = new ArrayList<>(Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8.));

        //Filter for Evens:
        System.out.println(numbers.stream().filter((x) -> x % 2 == 0).toList()); // [2.0, 4.0, 6.0, 8.0]

        //Sum:
        System.out.println(numbers.stream().reduce(0., (partialSum, el) -> partialSum + el)); //  36.0

        //Max:
        System.out.println(numbers.stream().reduce(numbers.get(0), (x, y) -> (x > y) ? x : y)); // 8.0
    }

}
