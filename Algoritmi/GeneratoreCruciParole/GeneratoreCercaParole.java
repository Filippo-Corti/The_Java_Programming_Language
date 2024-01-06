import java.util.Random;

public class GeneratoreCercaParole {

    public static void main(String[] args) {
        int righe = 20;
        int colonne = 20;
        String[] parole = {"JAVA", "PROGRAMMAZIONE", "CERCA", "PAROLE", "GPT"};

        char[][] griglia = new char[righe][colonne];
        inizializzaGriglia(griglia);
        inserisciParole(griglia, parole);
        riempiSpaziVuoti(griglia);
        stampaGriglia(griglia);
    }

    private static void inizializzaGriglia(char[][] griglia) {
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                griglia[i][j] = 0; // Inizializza tutti gli elementi a 0
            }
        }
    }

    private static void inserisciParole(char[][] griglia, String[] parole) {
        Random random = new Random();
        for (String parola : parole) {
            boolean inserita = false;

            while (!inserita) {
                int direzione = random.nextInt(3);
                int riga = random.nextInt(griglia.length);
                int colonna = random.nextInt(griglia[0].length);

                // Verifica se la parola può essere inserita senza collisioni
                if (verificaCollisione(griglia, parola, riga, colonna, direzione)) {
                    switch (direzione) {
                        case 0: // Orizzontale
                            for (int i = 0; i < parola.length(); i++) {
                                griglia[riga][colonna + i] = parola.charAt(i);
                            }
                            inserita = true;
                            break;
                        case 1: // Verticale
                            for (int i = 0; i < parola.length(); i++) {
                                griglia[riga + i][colonna] = parola.charAt(i);
                            }
                            inserita = true;
                            break;
                        case 2: // Diagonale
                            for (int i = 0; i < parola.length(); i++) {
                                griglia[riga + i][colonna + i] = parola.charAt(i);
                            }
                            inserita = true;
                            break;
                    }
                }
            }
        }
    }

    private static boolean verificaCollisione(char[][] griglia, String parola, int riga, int colonna, int direzione) {
        switch (direzione) {
            case 0: // Orizzontale
                if (colonna + parola.length() > griglia[0].length) {
                    return false; // Non c'è spazio sufficiente
                }
                for (int i = 0; i < parola.length(); i++) {
                    if (griglia[riga][colonna + i] != 0 && griglia[riga][colonna + i] != parola.charAt(i)) {
                        return false; // Collisione con un'altra parola
                    }
                }
                break;
            case 1: // Verticale
                if (riga + parola.length() > griglia.length) {
                    return false; // Non c'è spazio sufficiente
                }
                for (int i = 0; i < parola.length(); i++) {
                    if (griglia[riga + i][colonna] != 0 && griglia[riga + i][colonna] != parola.charAt(i)) {
                        return false; // Collisione con un'altra parola
                    }
                }
                break;
            case 2: // Diagonale
                if (riga + parola.length() > griglia.length || colonna + parola.length() > griglia[0].length) {
                    return false; // Non c'è spazio sufficiente
                }
                for (int i = 0; i < parola.length(); i++) {
                    if (griglia[riga + i][colonna + i] != 0 && griglia[riga + i][colonna + i] != parola.charAt(i)) {
                        return false; // Collisione con un'altra parola
                    }
                }
                break;
        }
        return true;
    }

    private static void riempiSpaziVuoti(char[][] griglia) {
        Random random = new Random();
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                if (griglia[i][j] == 0) {
                    griglia[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    private static void stampaGriglia(char[][] griglia) {
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                System.out.print(griglia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
