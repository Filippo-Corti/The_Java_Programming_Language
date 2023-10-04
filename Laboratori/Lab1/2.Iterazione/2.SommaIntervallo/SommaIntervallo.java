public class SommaIntervallo {
    
    public static void main(String[] args) {
        int s = 0;
        int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]);
        for (int i = a + 1; i < b; i++) {
            if (i % 2 == 0) s += i;
        }
        System.out.println("Somma = " + s);
    }
}
