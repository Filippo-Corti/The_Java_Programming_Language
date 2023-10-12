import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TopN {
    // OVERVIEW: a static class to find the N greatest numbers in an array

    private static int[] readIntArray(int n) {
        // REQUIRES: at least n ints scanned from standard input (nothing really happens if you don't, it still works)
        // EFFECTS: returns the scanned items in an array of ints
        // !!!Important: it ignores duplicated numbers
        // returns null if n < 1

        if (n < 1) {
            return null;
        }

        Set<Integer> array = new HashSet<>(); // Set permette di eliminare le ripetizioni in automatico

        System.out.println("Inserisci almeno " + n + " numeri interi diversi tra loro (CTRL+D per terminare):");
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            array.add(s.nextInt());
        }

        if (array.size() < n) {
            //throw new Exception NotEnoughElements
        }


        return array.stream().mapToInt(i -> i).toArray(); // Strano casting da set ad array passando per Stream
    }

    public static int[] highest(int[] numbers, int n) {
        // EFFECTS: returns the n greatest ints in numbers, in the form of an array
        // returns null if numbers is null or n <= 0
        // returns numbers if n >= length of numbers
        if (numbers == null || n <= 0)
            return null;

        if (numbers.length < n)
            return numbers;

        int[] greatests = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            int toSet = numbers[i];
            for (int j = 0; j < n; j++) {
                if (toSet > greatests[j]) {
                    int temp = toSet;
                    toSet = greatests[j];
                    greatests[j] = temp;
                }
            }
        }
        return greatests;

    }

    private static void printArray(int[] numbers) {
        // EFFECTS: prints numbers in the format [ n1, n2, n3, ... ]
        // if numbers is null the function ends with no output
        if (numbers == null)
            return;

        System.out.print("[ ");
        int i;
        for (i = 0; i < numbers.length - 1; i++) {
            System.out.print(numbers[i] + ", ");
        }
        if (i >= 0) // i is > 0 when the list is not empty
            System.out.print(numbers[i]);
        System.out.println(" ]");
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        /*
         * EDGE CASES tested:
         * printArray(null);
         * printArray(new int[] {});
         * printArray(highest(null, 3));
         * printArray(highest(new int[] { 1, 2, 3 }, 4));
         * printArray(highest(new int[] { 1, 2, 3 }, 0));
         * printArray(highest(new int[] { 1, 2, 3 }, 1));
         */

        int[] a = readIntArray(n);
        System.out.print("Letto: ");
        printArray(a);
        int[] h = highest(a, n);
        System.out.print("Numeri piu' alti: ");
        printArray(h);
    }

    /* L'unica procedura che necessiterebbe di un'eccezione è quella per la lettura degli interi, su cui non è effettuato alcun controllo per sapere se abbastanza numeri diversi sono stati inseriti. Le altre sono procedure totali */

}
