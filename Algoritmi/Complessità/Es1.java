
public class Es1 {
    
    public static void main(String[] args) {
        int c = 0;
        int N = (int)1E3;
        for (int i = 0; i < N; i++) {
            int j = 2 * i;
            while (j > i ) {
                if (i % 2 == 0) {
                    j = j/2;
                }
                j--;
                c++;
            }
        }
        System.out.println(c);
    }

}
