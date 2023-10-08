public class Terne {

    public static void main(String[] args) {
        int soglia = Integer.parseInt(args[0]);
        ternePitagorichePrimitive(soglia);
    }

    // Una terna pitagorica si dice primitiva se a, b e c non hanno divisori comuni
    public static void ternePitagorichePrimitive(int soglia) {
        System.out.println("Terne pitagorighe:");
        for (int i = 3; i < soglia - 2; i++) {
            for (int j = i + 1; j < soglia - 1; j++) {
                for (int k = j + 1; k < soglia; k++) {
                    if (ternaPitagorica(i, j, k) && MCD(i, MCD(j, k)) == 1) {
                        System.out.println("(" + i + ", " + j + ", " + k + ")");
                    }
                }
            }
        }
    }

    public static void ternePitagoriche(int soglia) {
        System.out.println("Terne pitagorighe:");
        for (int i = 3; i < soglia - 2; i++) {
            for (int j = i + 1; j < soglia - 1; j++) {
                for (int k = j + 1; k < soglia; k++) {
                    if (ternaPitagorica(i, j, k)) {
                        System.out.println("(" + i + ", " + j + ", " + k + ")");
                    }
                }
            }
        }
    }

    public static boolean ternaPitagorica(int a, int b, int c) {
        return (a * a + b * b == c * c);
    }

    private static int MCD(int a, int b) {
        int r;
        while (b != 0) {
          r = a % b;
          a = b;
          b = r;
        }
        return Math.abs(a);
      }
}
