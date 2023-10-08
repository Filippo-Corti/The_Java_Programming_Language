import java.util.Scanner;
import java.util.ArrayList;

public class MinMaxAvg {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            numbers.add(s.nextInt());
        }

        System.out.println("Minimo: " + minimo(numbers));
        System.out.println("Massimo: " + massimo(numbers));
        System.out.println("Valore medio: " + media(numbers));

    }

    public static int minimo(ArrayList<Integer> in) {
        int min = in.get(0); //Verrà riconotrollato ma pazienza
        for (int i : in) {
            if (min > i) min = i;
        }
        return min;
    }

    public static int massimo(ArrayList<Integer> in) {
        int max = in.get(0); //Verrà riconotrollato ma pazienza
        for (int i : in) {
            if (max < i) max = i;
        }
        return max;
    }

    public static float media(ArrayList<Integer> in) {
        float sum = 0;
        for (int i : in) {
            sum += i;
        }
        return sum/in.size();
    }
    
}
