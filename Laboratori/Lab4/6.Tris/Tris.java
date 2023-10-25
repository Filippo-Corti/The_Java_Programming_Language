import java.util.Scanner;

public class Tris {
    // OVERVIEW: classe mutabile che implementa il gioco del Tris tra due giocatori
    // reali

    // attributes
    char[][] tabellone;
    char turno;
    int contaMosse;

    final char CERCHIO = 'O';
    final char ICS = 'X';
    final char VUOTO = 0;

    // constructors
    public Tris() {
        // MODIFIES: this
        // EFFECTS: inizializza Tris ad un tabellone vuoto, con turno del giocatore 'O';
        tabellone = new char[3][3];
        turno = CERCHIO;

        assert repOk();
    }

    public boolean repOk() {
        if (contaMosse > 9 || contaMosse < 0)
            return false;
        if (turno != CERCHIO && turno != ICS)
            return false;
        if (tabellone == null)
            return false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabellone[i][j] != VUOTO && tabellone[i][j] != CERCHIO && tabellone[i][j] != ICS) {
                    return false;
                }
            }
        }

        return true;
    }

    public char turno() {
        // EFFECTS: ritorna il turno corrente ('X' o 'O')
        return turno;
    }

    public void cambiaTurno() {
        // MODIFIES: this.turno
        // EFFECTS: inverte il turno
        if (this.turno == ICS)
            this.turno = CERCHIO;
        else
            this.turno = ICS;

        assert repOk();
    }

    public void mossa(int x, int y) throws IllegalArgumentException {
        // MODIFIES: this.tabellone, this.mosse
        // EFFECTS: inserisce turno in tabellone[x-1][y-1]. Poi incrementa il contatore
        // di mosse di 1 
        // se la casella è occupata lancia IllegalArgumentException
        // se x e y non sono compresi tra 1 e 3 lancia IllegalArgumentException

        if (x < 1 || x > 3 || y < 1 || x > 3)
            throw new IllegalArgumentException("Coordinate non valide");

        if (tabellone[x - 1][y - 1] != VUOTO) {
            throw new IllegalArgumentException("Casella già occupata");
        }

        tabellone[x - 1][y - 1] = this.turno;

        this.contaMosse++;

        assert repOk();
    }

    @Override
    public String toString() {
        String res = "\n-----------------\n|R\\C| 1 | 2 | 3 |\n-----------------\n";
        for (int i = 0; i < 3; i++) {
            res += "| " + (i + 1) + " |";
            for (int j = 0; j < 3; j++) {
                if (tabellone[i][j] == VUOTO)
                    res += "   |";
                else
                    res += " " + tabellone[i][j] + " |";
            }
            res += "\n-----------------\n";
        }
        return res;
    }

    public boolean terminato() {
        // EFFECTS: ritorna true se sono già state effettuate 9 mosse;
        return (this.contaMosse >= 9);
    }

    public boolean vittoria() {
        // EFFECTS: ritorna true se il giocatore di turno ha vinto. False altrimenti
        for (int i = 0; i < 3; i++) {
            // Riga
            if (quadrEqual(this.turno, tabellone[i][0], tabellone[i][1], tabellone[i][2]))
                return true;
            // Colonna
            if (quadrEqual(this.turno, tabellone[0][i], tabellone[1][i], tabellone[2][i]))
                return true;
        }
        // Prima diagonale
        if (quadrEqual(this.turno, tabellone[0][0], tabellone[1][1], tabellone[2][2]))
            return true;
        // Seconda diagonale
        if (quadrEqual(this.turno, tabellone[0][2], tabellone[1][1], tabellone[2][0]))
            return true;

        return false;
    }

    private boolean quadrEqual(char a, char b, char c, char d) {
        // EFFECTS: returns a == b == c == d
        return (a == b && b == c && c == d);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Tris tris;
        boolean giocaAncora = true;
        while (giocaAncora) {
            tris = new Tris();
            System.out.println(tris);

            while (!tris.terminato()) {
                System.out.println("Mossa di " + tris.turno());
                System.out.println("Inserisci: x y");
                try {
                    tris.mossa(s.nextInt(), s.nextInt());
                    System.out.println(tris);
                    if (tris.vittoria()) {
                        System.out.println("Ha vinto " + tris.turno());
                        break;
                    }
                    tris.cambiaTurno();
                } catch (IllegalArgumentException e) {
                    System.out.println("Mossa non eseguita: " + e.getMessage());
                    System.out.println(tris);
                }
            }

            if (tris.terminato())
                System.out.println("Partita conclusa in pareggio");

            System.out.println("Un'altra partita? (S/s per si)");
            String in = s.next();
            if (!in.equals("S") && !in.equals("s"))
                giocaAncora = false;
        }
    }
}
