public class Pitagorica {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        stampaTavolaPitagorica(creaTavolaPitagorica(n));
    }

    public static int[][] creaTavolaPitagorica(int n) {
        int[][] tavola = new int[n][n];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                tavola[i - 1][j - 1] = i * j;
            }
        }
        return tavola;
    }

    public static void stampaTavolaPitagorica(int[][] s) {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                System.out.printf("%6d", s[i][j]);
            }
            System.out.println();
        }
    }

}
