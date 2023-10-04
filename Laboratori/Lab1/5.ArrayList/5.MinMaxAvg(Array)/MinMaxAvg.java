import java.util.Scanner;

public class MinMaxAvg {
    

    public static void main(String[] args) {
        int lines = Integer.parseInt(args[0]);
        int[] numbers = new int[lines];
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < lines; i++) {
            numbers[i] = s.nextInt();
        }

        System.out.println("Minimo: " + minimo(numbers));
        System.out.println("Massimo: " + massimo(numbers));
        System.out.println("Valore medio: " + media(numbers));

    }

    public static int minimo(int[] in) {
        int min = in[0];
        for (int i = 1; i < in.length; i++) {
            if (min > in[i]) min = in[i];
        }
        return min;
    }

    public static int massimo(int[] in) {
        int max = in[0];
        for (int i = 1; i < in.length; i++) {
            if (max < in[i]) max = in[i];
        }
        return max;
    }

    public static float media(int[] in) {
        float sum = 0;
        for (int i = 0; i < in.length; i++) {
            sum += in[i];
        }
        return sum/in.length;
    }
    
}
