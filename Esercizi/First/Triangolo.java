import ClassiAstratte.Triangolo;

public class Triangolo {

    private int base;
    private int altezza;

    public Triangolo() {
    }

    public Triangolo(int base, int altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    public int Area() {
        return this.base * this.altezza / 2;
    }

    public static void main(String[] args) {
        Triangolo t1 = new Triangolo(12, 4);
        Triangolo t2 = new Triangolo(5, 2);
        Triangolo t3 = new Triangolo(6, 3);
        Triangolo[] triangoli = { t1, t2, t3 };


        Triangolo[] altriTriangoli = triangoli;

        altriTriangoli[0].base = 8; //Va a modificare entrambi

        stampaAreeTriangoli(triangoli);
        stampaAreeTriangoli(altriTriangoli);

        Triangolo[] altriTriangoliAncora = triangoli.clone();

        altriTriangoliAncora[0].base = 2; //Va a modificare entrambi

        stampaAreeTriangoli(triangoli);
        stampaAreeTriangoli(altriTriangoliAncora);

    

    }

    public static void stampaAreeTriangoli(Triangolo[] triangoli) {
        System.out.println("--------------------");
        int i = 1;
        for (Triangolo triangolo : triangoli) {
            System.out.println("Triangolo numero " + (i++) + " ha area = " + triangolo.Area());
        }

        /*
         * for (int i = 0; i < triangoli.length; i++) {
         * System.out.println("Triangolo numero " + (i + 1) + " ha area = " +
         * triangoli[i].Area());
         * }
         */
    }
}
