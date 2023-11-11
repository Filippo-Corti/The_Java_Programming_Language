import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Matrice implements Iterable<Iterator<Integer>> {
    // OVERVIEW: classe mutabile che descrive una matrice di dimensioni n * m.
    // Fornisce un sistema di iterazione sui suoi elementi, riga per riga, elemento
    // per elemento

    // attributes
    private int[][] matrix;

    // constructors
    public Matrice(int n, int m) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: istanzia una nuova Matrice di dimensioni n * m,
        // inizializzata con tutti gli elementi a zero
        // lancia IllegalArgumentException se n o m sono <= 0
        if (n <= 0 || m <= 0)
            throw new IllegalArgumentException("Valori per righe/colonne matrice non validi");
        matrix = new int[n][m];

        assert repOk();
    }

    // methods
    public void set(int r, int c, int value) throws IndexOutOfBoundsException {
        // MODIFIES: this
        // EFFECTS: matrice[r][c] = value
        // lancia IndexOutOfBoundException se r e c non sono nel range della matrice
        matrix[r][c] = value;

        assert repOk();
    }

    public int get(int r, int c) throws IndexOutOfBoundsException {
        // EFFECTS: ritorna matrice[r][c]
        // lancia IndexOutOfBoundsException se r e c non sono nel range della matrice
        return matrix[r][c];
    }

    public int getRows() {
        // EFFECTS: ritorna il numero di righe della matrice
        return matrix.length;
    }

    public int getCols() {
        // EFFECTS: ritorna il numero di colonne della matrice
        return matrix[0].length;
    }

    public boolean repOk() {
        if (matrix == null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        String res = "";
        for (int[] row : matrix) {
            for (int i : row) {
                res += i + " ";
            }
            res += "\n";
        }
        return res;
    }

    // iterators
    @Override
    public Iterator<Iterator<Integer>> iterator() {
        // EFFECTS: restituisce un iterator che itera sulle righe della matrice,
        // restituendo ad ogni next() un Iterator<Integer> che scorre la i-esima riga
        // della matrice
        return new Iterator<Iterator<Integer>>() {

            int currentRow = 0;

            @Override
            public boolean hasNext() {
                return currentRow < matrix.length;
            }

            @Override
            public Iterator<Integer> next() {
                // EFFECTS: restituisce un iterator che itera sugli elementi dell' i-esima riga
                // della matrice, fino a quando non terminano

                if (!hasNext())
                    throw new NoSuchElementException("La matrice non ha ulteriori righe");

                // Purtroppo devo salvarmi il valore di currentRow per usarlo nel return, prima
                // di incrementarlo
                int row = currentRow++;

                return new Iterator<Integer>() {

                    int currentEl = 0;

                    @Override
                    public boolean hasNext() {
                        return currentEl < matrix[row].length;
                    }

                    @Override
                    public Integer next() {
                        if (!hasNext())
                            throw new NoSuchElementException("La riga della matrice non ha ulteriori elementi");

                        return matrix[row][currentEl++];
                    }

                };
            }

        };
    }

    public static void main(String[] args) {
        Matrice matrice = null;
        Scanner s = new Scanner(System.in);

        try {
            matrice = new Matrice(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for (int i = 0; i < matrice.getRows(); i++) {
            System.out.println(
                    "Inserisci i " + matrice.getCols() + " numeri (separati da spazio) della riga " + (i + 1));
            for (int j = 0; j < matrice.getCols(); j++) {
                matrice.set(i, j, s.nextInt());
            }
        }

        System.out.println("Matrice inserita:");
        // System.out.println(matrice);

        for (Iterator<Integer> riga : matrice) {
            while (riga.hasNext())
                System.out.print(riga.next() + " ");
            System.out.println();
        }
    }
}
