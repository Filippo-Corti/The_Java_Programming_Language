public interface PoligonoRegolare {
    
    public int getNumeroLati();

    public double getLunghezzaLato();

    public double getPerimetro();

    default double getArea() {
        throw new UnsupportedOperationException("Calcolo dell'Area non supportato");
    }
}
