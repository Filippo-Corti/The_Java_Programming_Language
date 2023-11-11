public class Triangolo implements PoligonoRegolare{

    double lunghezzaLato;

    public Triangolo(double lunghezzaLato) {
        this.lunghezzaLato = lunghezzaLato;
    }

    @Override
    public int getNumeroLati() {
        return 3;
    }

    @Override
    public double getLunghezzaLato() {
        return this.lunghezzaLato;
    }

    @Override
    public double getPerimetro() {
        return lunghezzaLato * 3;
    }

    public static void main(String[] args) {
        PoligonoRegolare p = new Triangolo(8);
        System.out.println(p.getPerimetro());
        //p.numeroLati = 5; //Questa cosa non posso nemmeno farla!
        System.out.println(p.getPerimetro());
    }
    
}
