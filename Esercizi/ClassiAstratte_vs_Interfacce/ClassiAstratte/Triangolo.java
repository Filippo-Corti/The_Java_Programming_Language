
public class Triangolo extends PoligonoRegolare {

    public Triangolo(double lunghezzaLato) {
        this.lunghezzaLato = lunghezzaLato;
        this.numeroLati = 3;
    }

    @Override
    public double getArea() {
        return lunghezzaLato * lunghezzaLato * Math.sqrt(3) / 4;
    }

    public boolean repOk() {
        if (numeroLati != 3)
            return false;

        assert super.repOk();
        
        return true;
    }

    public static void main(String[] args) {
        PoligonoRegolare p = new Triangolo(8);
        System.out.println(p.getPerimetro());
        p.numeroLati = 5; // Nessuno mi impedisce di dire che un Triangolo ha 5 lati?
        System.out.println(p.getPerimetro());
    }

}
