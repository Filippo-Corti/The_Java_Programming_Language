public abstract class PoligonoRegolare {

    int numeroLati;
    double lunghezzaLato;

    public int getNumeroLati() {
        return numeroLati;
    }

    public double getLunghezzaLato() {
        return lunghezzaLato;
    }

    public double getPerimetro() {
        return numeroLati * lunghezzaLato;
    }

    public abstract double getArea();

    public boolean repOk() {
        if (numeroLati <= 0)
            return false;

        if (lunghezzaLato <= 0)
            return false;
        
        return true;
    } 

}
