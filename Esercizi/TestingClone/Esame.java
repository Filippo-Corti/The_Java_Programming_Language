public class Esame implements Cloneable {

    int voto;
    String corso;
    TipoMutabile mutabile;

    public Esame(int voto, String corso, TipoMutabile mutabile) {
        this.voto = voto;
        this.corso = corso;
        this.mutabile = mutabile;
    }

    @Override
    public String toString() {
        return corso + ": " + voto + " - " + mutabile;
    }

    @Override
    public Object clone() {
        Esame e = null;
        try {
            e = (Esame) super.clone();
        } catch (CloneNotSupportedException c) {
            e = new Esame(voto, corso, mutabile);
        }
        e.mutabile = (TipoMutabile) this.mutabile.clone(); //Se TipoMutabile implementa clone
        //Altrimenti: e.mutabile = new TipoMutabile(s); (che va bene perché s è immutabile, se no ancora problemi)
        return e;
    }

    public static void main(String[] args) {

        TipoMutabile mutabile = new TipoMutabile("Ciao");
        int voto = 18;

        Esame e1 = new Esame(voto, "Programmazione", mutabile);

        Esame e2 = (Esame) e1.clone();
        e2.voto = 30; // Non cambia e1
        e2.mutabile.setS("Non piu ciao"); // Questo cambia entrambi (Problema della SHALLOW COPY) => Implemento un
                                          // metodo clone ad-hoc
        System.out.println(e1);
        System.out.println(e2);

    }

}
