public class Mutabile implements Cloneable {

    private String s;

    public Mutabile() {
    }

    public Mutabile(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {

        // STRINGA è NON-MUTABILE

        String stringa = "Ciao";
        String stringa2 = stringa;

        stringa2 = "ciao"; // Questo modifica solo stringa2
        System.out.println(stringa2);
        System.out.println(stringa);
        System.out.println("----------------------");

        // GLI OGGETTI sono MUTABILI

        Mutabile m1 = new Mutabile("Ciao");
        Mutabile m2 = m1;

        m2.s = "ciao"; // Questo modifica sia m1 che m2
        System.out.println(m1);
        System.out.println(m2);
        System.out.println("----------------------");

        Mutabile m3 = new Mutabile("Ciao");
        Mutabile m4;
        try {
            m4 = (Mutabile) m3.clone();
            m4.s = "ciao"; // Questo modifica solo m4 (Poiché ho CLONATO l'oggetto invece di fare obj1 =
                           // obj2)
            System.out.println(m3);
            System.out.println(m4);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
