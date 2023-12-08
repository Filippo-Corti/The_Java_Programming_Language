public class Carta implements Comparable<Carta> {
    // OVERVIEW: classe che descrive una Carta da gioco. Immutabile

    // attributes
    private final int valore; // A=1,2,3,4,5,6,7,8,9,10,J=11,Q=12,K=13
    private final Seme seme;

    // constructors
    public Carta(int valore, Seme seme) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inializza una nuova Carta
        // lancia IllegalArgumentException se valore non è un valore valido

        if (valore < 1 || valore > 13)
            throw new IllegalArgumentException("Valore non valido");

        this.valore = valore;
        this.seme = seme;
    }

    // methods
    public int getValore() {
        return valore;
    }

    public Seme getSeme() {
        return seme;
    }

    @Override
    public String toString() {
        return "Carta: " + valore + " " + seme;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carta other = (Carta) obj;
        if (valore != other.valore)
            return false;
        if (seme != other.seme)
            return false;
        return true;
    }

    @Override
    public int compareTo(Carta o) {
        if (this.getValore() != o.getValore())
            return Integer.compare(this.getValore(), o.getValore());

        return this.getSeme().compareTo(o.getSeme());
    }

    public static Carta stringToCard(String s) {
        //EFFECTS: ritorna la Carta corrispondente a s
        //  lancia IllegalArgumentException se s non è del formato: <Numero> <Seme> o se tali valori sono non validi
        String in[] = s.split(" ");
        int valore = Integer.parseInt(in[0]);
        if (valore < 1 || valore > 13)
            throw new IllegalArgumentException("Stringa non valida");
        Seme seme = null;
        switch (in[1]) {
            case "C":
                seme = Seme.CUORI;
                break;
            case "Q":
                seme = Seme.QUADRI;
                break;
            case "F":
                seme = Seme.FIORI;
                break;
            case "P":
                seme = Seme.PICCHE;
                break;
            default:
                throw new IllegalArgumentException("Stringa non valida");
        }

        return new Carta(valore, seme);
    }

}
